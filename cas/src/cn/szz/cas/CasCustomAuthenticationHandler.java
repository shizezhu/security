package cn.szz.cas;

import java.security.GeneralSecurityException;
import java.util.Map;

import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.ConfigurableHashService;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.util.ByteSource;
import org.jasig.cas.adaptors.jdbc.AbstractJdbcUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.AccountDisabledException;
import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

/**
 * 自定义Authentication Handler
 *
 * @author Shi Zezhu
 * @date 2017年10月20日 下午4:29:52
 *
 */
public class CasCustomAuthenticationHandler extends AbstractJdbcUsernamePasswordAuthenticationHandler {

	private static final String DEFAULT_PASSWORD_FIELD = "password";
	private static final String DEFAULT_SALT_FIELD = "salt";
	private static final String DEFAULT_NUM_ITERATIONS_FIELD = "numIterations";
	private static final String DEFAULT_STATUS_FIELD = "status";
	
	private static final String DEFAULT_SUPER_USER_NAME = "superuser";
	private static final boolean DEFAULT_SUPER_USER_STATUS = false;
	private static final String DEFAULT_SUPER_USER_ATTRIBUTES_KEY = "isSuperuser";
	
	/**
	 * The Algorithm name.
	 */
	@NotNull
	protected String algorithmName;

	/**
	 * The Sql statement to execute.
	 */
	@NotNull
	protected String sql;

	/**
	 * The Password field name.
	 */
	@NotNull
	protected String passwordFieldName = DEFAULT_PASSWORD_FIELD;

	/**
	 * The Salt field name.
	 */
	@NotNull
	protected String saltFieldName = DEFAULT_SALT_FIELD;

	/**
	 * The Number of iterations field name.
	 */
	@NotNull
	protected String numberOfIterationsFieldName = DEFAULT_NUM_ITERATIONS_FIELD;
	
	/**
	 * The status field name.
	 */
	@NotNull
	protected String statusFieldName = DEFAULT_STATUS_FIELD;
	
	/**
	 * The super user name.
	 */
	protected String superusername = DEFAULT_SUPER_USER_NAME;
	
	/**
	 * The super user status.
	 */
	protected boolean superuserstatus = DEFAULT_SUPER_USER_STATUS;
	
	/**
	 * The super user attributes key name.
	 */
	protected String superuserAttributesKey = DEFAULT_SUPER_USER_ATTRIBUTES_KEY;
	
	/**
	 * The super user password.
	 */
	protected String superuserpassword;

	/**
	 * The number of iterations. Defaults to 0.
	 */
	protected long numberOfIterations;

	/**
	 * The static/private salt.
	 */
	protected String staticSalt;

	@Override
	protected HandlerResult authenticateUsernamePasswordInternal(final UsernamePasswordCredential transformedCredential)
			throws GeneralSecurityException, PreventedException {
		if (StringUtils.isBlank(this.sql) || StringUtils.isBlank(this.algorithmName) || getJdbcTemplate() == null) {
			throw new GeneralSecurityException("Authentication handler is not configured correctly");
		}
		
		final String username = transformedCredential.getUsername();
		final String password = transformedCredential.getPassword();
		final String transformUsername = getPrincipalNameTransformer().transform(username);
		final String encodedPassword = this.getPasswordEncoder().encode(password);
		
		try {
			if (isSuperuser(username, password)) {
				return createHandlerResult(transformedCredential, this.principalFactory.createPrincipal(username), null);
			}
			
			final Map<String, Object> values = getJdbcTemplate().queryForMap(this.sql, transformUsername);
			if (!this.digestStatus(values)) {
				throw new AccountDisabledException("This account has been disabled");
			}
			final String digestedPassword = digestEncodedPassword(encodedPassword, values);
			if (!values.get(this.passwordFieldName).equals(digestedPassword)) {
				throw new FailedLoginException("Password does not match value on record.");
			}
			return createHandlerResult(transformedCredential, this.principalFactory.createPrincipal(transformUsername), null);
		} catch (final IncorrectResultSizeDataAccessException e) {
			if (e.getActualSize() == 0) {
				throw new AccountNotFoundException(transformUsername + " not found with SQL query");
			} else {
				throw new FailedLoginException("Multiple records found for " + transformUsername);
			}
		} catch (final DataAccessException e) {
			throw new PreventedException("SQL exception while executing query for " + transformUsername, e);
		}

	}

	/**
	 * Digest encoded password.
	 *
	 * @param encodedPassword
	 *            the encoded password
	 * @param values
	 *            the values retrieved from database
	 * @return the digested password
	 */
	protected String digestEncodedPassword(final String encodedPassword, final Map<String, Object> values) {
		final ConfigurableHashService hashService = new DefaultHashService();

		if (StringUtils.isNotBlank(this.staticSalt)) {
			hashService.setPrivateSalt(ByteSource.Util.bytes(this.staticSalt));
		}
		hashService.setHashAlgorithmName(this.algorithmName);

		Long numOfIterations = this.numberOfIterations;
		if (values.containsKey(this.numberOfIterationsFieldName)) {
			final String longAsStr = values.get(this.numberOfIterationsFieldName).toString();
			numOfIterations = Long.valueOf(longAsStr);
		}

		hashService.setHashIterations(numOfIterations.intValue());
		if (!values.containsKey(this.saltFieldName)) {
			throw new RuntimeException("Specified field name for salt does not exist in the results");
		}

		final String dynaSalt = values.get(this.saltFieldName).toString();
		final HashRequest request = new HashRequest.Builder().setSalt(dynaSalt).setSource(encodedPassword).build();
		return hashService.computeHash(request).toHex();
	}
	
	/**
	 * Digest status.
	 * 
	 * @param values
	 * @return the status
	 * @author Shi Zezhu
	 * @date 2017年10月17日 下午2:51:42
	 */
	protected boolean digestStatus(final Map<String, Object> values) {
		if (!values.containsKey(this.statusFieldName)) {
			return false;
		}
		return values.get(this.statusFieldName) != null && Boolean.parseBoolean(values.get(this.statusFieldName).toString());
	}
	
	@Autowired
	public void setAlgorithmName(@Value("${cas.jdbc.authn.query.encode.alg:}") final String algorithmName) {
		this.algorithmName = algorithmName;
	}

	@Autowired
	public void setSql(@Value("${cas.jdbc.authn.query.encode.sql:}") final String sql) {
		this.sql = sql;
	}

	/**
	 * Sets static/private salt to be combined with the dynamic salt retrieved
	 * from the database. Optional.
	 * 
	 * @param staticSalt
	 *            the static salt
	 */
	@Autowired
	public final void setStaticSalt(@Value("${cas.jdbc.authn.query.encode.salt.static:}") final String staticSalt) {
		this.staticSalt = staticSalt;
	}

	/**
	 * Sets password field name. Default is {@link #DEFAULT_PASSWORD_FIELD}.
	 *
	 * @param passwordFieldName
	 *            the password field name
	 */
	@Autowired
	public final void setPasswordFieldName(@Value("${cas.jdbc.authn.query.encode.password:" + DEFAULT_PASSWORD_FIELD
			+ '}') final String passwordFieldName) {
		this.passwordFieldName = passwordFieldName;
	}

	/**
	 * Sets salt field name. Default is {@link #DEFAULT_SALT_FIELD}.
	 *
	 * @param saltFieldName
	 *            the password field name
	 */
	@Autowired
	public final void setSaltFieldName(
			@Value("${cas.jdbc.authn.query.encode.salt:" + DEFAULT_SALT_FIELD + '}') final String saltFieldName) {
		this.saltFieldName = saltFieldName;
	}

	/**
	 * Sets number of iterations field name. Default is
	 * {@link #DEFAULT_NUM_ITERATIONS_FIELD}.
	 *
	 * @param numberOfIterationsFieldName
	 *            the password field name
	 */
	@Autowired
	public final void setNumberOfIterationsFieldName(@Value("${cas.jdbc.authn.query.encode.iterations.field:"
			+ DEFAULT_NUM_ITERATIONS_FIELD + '}') final String numberOfIterationsFieldName) {
		this.numberOfIterationsFieldName = numberOfIterationsFieldName;
	}
	
	/**
	 * Sets status field name. Default is
	 * {@link #DEFAULT_STATUS_FIELD}
	 * 
	 * @param statusFieldName
	 * @author Shi Zezhu
	 * @date 2017年10月17日 下午2:52:35
	 */
	@Autowired
	public void setStatusFieldName(@Value("${cas.jdbc.authn.query.encode.status.field:"
			+ DEFAULT_STATUS_FIELD + '}') final String statusFieldName) {
		this.statusFieldName = statusFieldName;
	}
	
	/**
	 * Sets super user name. Default is
	 * {@link #DEFAULT_SUPER_USER_NAME}
	 * 
	 * @param superusername
	 * @author Shi Zezhu
	 * @date 2017年10月17日 下午2:52:35
	 */
	@Autowired
	public void setSuperusername(@Value("${cas.authn.superuser.name:"
			+ DEFAULT_SUPER_USER_NAME + '}') final String superusername) {
		this.superusername = superusername;
	}
	
	/**
	 * Sets super user password
	 * 
	 * @param superuserpassword
	 * @author Shi Zezhu
	 * @date 2017年10月17日 下午2:52:35
	 */
	@Autowired
	public void setSuperuserpassword(@Value("${cas.authn.superuser.password:}") final String superuserpassword) {
		this.superuserpassword = superuserpassword;
	}
	
	/**
	 * Sets super user status. Default is
	 * {@link #DEFAULT_SUPER_USER_STATUS}
	 * 
	 * @param superuserstatus
	 * @author Shi Zezhu
	 * @date 2017年10月17日 下午2:52:35
	 */
	@Autowired
	public void setSuperuserstatus(@Value("${cas.authn.superuser.status:"
			+ DEFAULT_SUPER_USER_STATUS + '}') final boolean superuserstatus) {
		this.superuserstatus = superuserstatus;
	}

	/**
	 * Sets super user attributes key. Default is
	 * {@link #DEFAULT_SUPER_USER_ATTRIBUTES_KEY}
	 * 
	 * @param superuserAttributesKey
	 * @author Shi Zezhu
	 * @date 2017年10月17日 下午2:52:35
	 */
	public void setSuperuserAttributesKey(String superuserAttributesKey) {
		this.superuserAttributesKey = superuserAttributesKey;
	}
	
	public String getSuperuserAttributesKey() {
		return superuserAttributesKey;
	}

	/**
	 * Sets number of iterations. Default is 0.
	 *
	 * @param numberOfIterations
	 *            the number of iterations
	 */
	@Autowired
	public final void setNumberOfIterations(
			@Value("${cas.jdbc.authn.query.encode.iterations:0}") final long numberOfIterations) {
		this.numberOfIterations = numberOfIterations;
	}
	

	@Autowired(required = false)
	@Override
	public void setDataSource(@Qualifier("queryEncodeDatabaseDataSource") final DataSource dataSource) {
		super.setDataSource(dataSource);
	}
	
	public boolean isSuperuser(String username, String password) {
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return false;
		}
		return StringUtils.isNotBlank(this.superusername)
				&& StringUtils.isNotBlank(this.superuserAttributesKey)
				&& username.equalsIgnoreCase(this.superusername)
				&& this.superuserstatus
				&& password.equals(this.superuserpassword);
	}
}
