package client.conn_ceximport_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")
/**
 * Job: conn_ceximport Purpose: Conector SAP x SMART - Nota Fiscal de Importação<br>
 * Description: Conector SAP x SMART - Nota Fiscal de Importação <br>
 * @author sergio.paes@slaterit.com
 * @version 6.2.1.20160704_1411
 * @status 
 */
public class conn_ceximport implements TalendJob {

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset
			.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends java.util.Properties {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

			if (database != null) {

				this.setProperty("database", database.toString());

			}

			if (host != null) {

				this.setProperty("host", host.toString());

			}

			if (pass != null) {

				this.setProperty("pass", pass.toString());

			}

			if (port != null) {

				this.setProperty("port", port.toString());

			}

			if (schema != null) {

				this.setProperty("schema", schema.toString());

			}

			if (username != null) {

				this.setProperty("username", username.toString());

			}

			if (tabela != null) {

				this.setProperty("tabela", tabela.toString());

			}

			if (datade != null) {

				this.setProperty("datade", datade.toString());

			}

			if (dataate != null) {

				this.setProperty("dataate", dataate.toString());

			}

			if (clientSap != null) {

				this.setProperty("clientSap", clientSap.toString());

			}

			if (userID != null) {

				this.setProperty("userID", userID.toString());

			}

			if (password != null) {

				this.setProperty("password", password.toString());

			}

			if (language != null) {

				this.setProperty("language", language.toString());

			}

			if (hostName != null) {

				this.setProperty("hostName", hostName.toString());

			}

			if (systemNumber != null) {

				this.setProperty("systemNumber", systemNumber.toString());

			}

			if (empresa != null) {

				this.setProperty("empresa", empresa.toString());

			}

		}

		public String database;

		public String getDatabase() {
			return this.database;
		}

		public String host;

		public String getHost() {
			return this.host;
		}

		public String pass;

		public String getPass() {
			return this.pass;
		}

		public String port;

		public String getPort() {
			return this.port;
		}

		public String schema;

		public String getSchema() {
			return this.schema;
		}

		public String username;

		public String getUsername() {
			return this.username;
		}

		public String tabela;

		public String getTabela() {
			return this.tabela;
		}

		public String datade;

		public String getDatade() {
			return this.datade;
		}

		public String dataate;

		public String getDataate() {
			return this.dataate;
		}

		public String clientSap;

		public String getClientSap() {
			return this.clientSap;
		}

		public String userID;

		public String getUserID() {
			return this.userID;
		}

		public String password;

		public String getPassword() {
			return this.password;
		}

		public String language;

		public String getLanguage() {
			return this.language;
		}

		public String hostName;

		public String getHostName() {
			return this.hostName;
		}

		public String systemNumber;

		public String getSystemNumber() {
			return this.systemNumber;
		}

		public String empresa;

		public String getEmpresa() {
			return this.empresa;
		}
	}

	private ContextProperties context = new ContextProperties();

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "conn_ceximport";
	private final String projectName = "client";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	public void setDataSources(
			java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources
				.entrySet()) {
			talendDataSources.put(
					dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry
							.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(
			new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent,
				final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null
						&& currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE",
							getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE",
						getExceptionCauseMessage(e));
				System.err
						.println("Exception in component " + currentComponent);
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					conn_ceximport.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass()
							.getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(conn_ceximport.this, new Object[] { e,
									currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tSAPConnection_1_error(Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tSAPConnection_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tSAPInput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tSAPInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tSAPInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tReplaceNull_1_error(Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tSAPInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tReplace_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tSAPInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMSSqlOutput_1_error(Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tSAPInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tSAPConnection_1_onSubJobError(Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread
				.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(),
				ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tSAPInput_1_onSubJobError(Exception exception,
			String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread
				.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(),
				ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tSAPConnection_1Process(
			final java.util.Map<String, Object> globalMap)
			throws TalendException {
		globalMap.put("tSAPConnection_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {

			String currentMethodName = new java.lang.Exception()
					.getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if (resumeEntryMethodName == null || resumeIt || globalResumeTicket) {// start
																					// the
																					// resume
				globalResumeTicket = true;

				/**
				 * [tSAPConnection_1 begin ] start
				 */

				ok_Hash.put("tSAPConnection_1", false);
				start_Hash.put("tSAPConnection_1", System.currentTimeMillis());

				currentComponent = "tSAPConnection_1";

				int tos_count_tSAPConnection_1 = 0;

				final String decryptedPassword_tSAPConnection_1 = context.password;

				com.sap.conn.jco.JCoDestination dest_tSAPConnection_1 = null;
				org.talend.sap.TSAPDestinationData destinationData_tSAPConnection_1 = null;

				java.util.Properties properties_tSAPConnection_1 = new java.util.Properties();

				destinationData_tSAPConnection_1 = new org.talend.sap.TSAPApplicationServerDestData.Builder(
						context.clientSap, context.userID,
						decryptedPassword_tSAPConnection_1, context.language,
						context.hostName, context.systemNumber)

				.setCustomProp(properties_tSAPConnection_1).build();

				dest_tSAPConnection_1 = org.talend.sap.TSAPDestinationFactory
						.getInstance().getDestination(
								destinationData_tSAPConnection_1);
				// Begins the stateful call sequence for calls to the specified
				// destination.
				com.sap.conn.jco.JCoContext.begin(dest_tSAPConnection_1);
				dest_tSAPConnection_1.ping();
				globalMap.put("conn_tSAPConnection_1", dest_tSAPConnection_1);

				/**
				 * [tSAPConnection_1 begin ] stop
				 */

				/**
				 * [tSAPConnection_1 main ] start
				 */

				currentComponent = "tSAPConnection_1";

				tos_count_tSAPConnection_1++;

				/**
				 * [tSAPConnection_1 main ] stop
				 */

				/**
				 * [tSAPConnection_1 end ] start
				 */

				currentComponent = "tSAPConnection_1";

				ok_Hash.put("tSAPConnection_1", true);
				end_Hash.put("tSAPConnection_1", System.currentTimeMillis());

				/**
				 * [tSAPConnection_1 end ] stop
				 */
			}// end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil
						.addLog("CHECKPOINT",
								"CONNECTION:SUBJOB_OK:tSAPConnection_1:OnSubjobOk",
								"", Thread.currentThread().getId() + "", "",
								"", "", "", "");
			}

			tSAPInput_1Process(globalMap);

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent,
					globalMap);

			throw te;
		} catch (java.lang.Error error) {

			throw error;
		} finally {

			try {

				/**
				 * [tSAPConnection_1 finally ] start
				 */

				currentComponent = "tSAPConnection_1";

				/**
				 * [tSAPConnection_1 finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tSAPConnection_1_SUBPROCESS_STATE", 1);
	}

	public static class row2Struct implements
			routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_client_conn_ceximport = new byte[0];
		static byte[] commonByteArray_client_conn_ceximport = new byte[0];

		public String EXCODIGOEMPRESA;

		public String getEXCODIGOEMPRESA() {
			return this.EXCODIGOEMPRESA;
		}

		public String EXMODELODOCUMENTO;

		public String getEXMODELODOCUMENTO() {
			return this.EXMODELODOCUMENTO;
		}

		public String EXSERIEDOCUMENTO;

		public String getEXSERIEDOCUMENTO() {
			return this.EXSERIEDOCUMENTO;
		}

		public String EXNUMERODOCUMENTO;

		public String getEXNUMERODOCUMENTO() {
			return this.EXNUMERODOCUMENTO;
		}

		public java.util.Date EXDATAEMISSAO;

		public java.util.Date getEXDATAEMISSAO() {
			return this.EXDATAEMISSAO;
		}

		public String EXCFOP;

		public String getEXCFOP() {
			return this.EXCFOP;
		}

		public String EXCODIGOFORNECEDOR;

		public String getEXCODIGOFORNECEDOR() {
			return this.EXCODIGOFORNECEDOR;
		}

		public String EXTIPODECL;

		public String getEXTIPODECL() {
			return this.EXTIPODECL;
		}

		public String EXNUMERODECLARACAO;

		public String getEXNUMERODECLARACAO() {
			return this.EXNUMERODECLARACAO;
		}

		public java.util.Date EXDATADECLARACAO;

		public java.util.Date getEXDATADECLARACAO() {
			return this.EXDATADECLARACAO;
		}

		public java.util.Date EXDATAENTRADA;

		public java.util.Date getEXDATAENTRADA() {
			return this.EXDATAENTRADA;
		}

		public String EXVALORTOTAL;

		public String getEXVALORTOTAL() {
			return this.EXVALORTOTAL;
		}

		public java.util.Date EXDATAEMBARQUE;

		public java.util.Date getEXDATAEMBARQUE() {
			return this.EXDATAEMBARQUE;
		}

		public String EXNUMEROCONHECIMENTO;

		public String getEXNUMEROCONHECIMENTO() {
			return this.EXNUMEROCONHECIMENTO;
		}

		public String EXDATACONHECIMENTO;

		public String getEXDATACONHECIMENTO() {
			return this.EXDATACONHECIMENTO;
		}

		public String EXTIPOCONHECIMENTO;

		public String getEXTIPOCONHECIMENTO() {
			return this.EXTIPOCONHECIMENTO;
		}

		public String EXNOMEPAIS;

		public String getEXNOMEPAIS() {
			return this.EXNOMEPAIS;
		}

		public String EXCODIGOPAIS;

		public String getEXCODIGOPAIS() {
			return this.EXCODIGOPAIS;
		}

		public java.util.Date EXDATASISCOMEX;

		public java.util.Date getEXDATASISCOMEX() {
			return this.EXDATASISCOMEX;
		}

		public java.util.Date EXDATADESEMBARACO;

		public java.util.Date getEXDATADESEMBARACO() {
			return this.EXDATADESEMBARACO;
		}

		public String EXVALORMERCADORIAS;

		public String getEXVALORMERCADORIAS() {
			return this.EXVALORMERCADORIAS;
		}

		public String EXVALORPIS;

		public String getEXVALORPIS() {
			return this.EXVALORPIS;
		}

		public String EXVALORCOFINS;

		public String getEXVALORCOFINS() {
			return this.EXVALORCOFINS;
		}

		public String EXVALORADUANA;

		public String getEXVALORADUANA() {
			return this.EXVALORADUANA;
		}

		public String EXVALORADUANAICMS;

		public String getEXVALORADUANAICMS() {
			return this.EXVALORADUANAICMS;
		}

		public String EXVALORIOF;

		public String getEXVALORIOF() {
			return this.EXVALORIOF;
		}

		public String EXVALORIMPOSTOIMPORT;

		public String getEXVALORIMPOSTOIMPORT() {
			return this.EXVALORIMPOSTOIMPORT;
		}

		public String EXVALORBASEIPI;

		public String getEXVALORBASEIPI() {
			return this.EXVALORBASEIPI;
		}

		public String EXVALORIPI;

		public String getEXVALORIPI() {
			return this.EXVALORIPI;
		}

		public String EXVALORBASEICM;

		public String getEXVALORBASEICM() {
			return this.EXVALORBASEICM;
		}

		public String EXVALORICM;

		public String getEXVALORICM() {
			return this.EXVALORICM;
		}

		public String EXLOCDESEMB;

		public String getEXLOCDESEMB() {
			return this.EXLOCDESEMB;
		}

		public String EXUFDESEMB;

		public String getEXUFDESEMB() {
			return this.EXUFDESEMB;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_client_conn_ceximport.length) {
					if (length < 1024
							&& commonByteArray_client_conn_ceximport.length == 0) {
						commonByteArray_client_conn_ceximport = new byte[1024];
					} else {
						commonByteArray_client_conn_ceximport = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_client_conn_ceximport, 0,
						length);
				strReturn = new String(
						commonByteArray_client_conn_ceximport, 0, length,
						utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos)
				throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis)
				throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos)
				throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_client_conn_ceximport) {

				try {

					int length = 0;

					this.EXCODIGOEMPRESA = readString(dis);

					this.EXMODELODOCUMENTO = readString(dis);

					this.EXSERIEDOCUMENTO = readString(dis);

					this.EXNUMERODOCUMENTO = readString(dis);

					this.EXDATAEMISSAO = readDate(dis);

					this.EXCFOP = readString(dis);

					this.EXCODIGOFORNECEDOR = readString(dis);

					this.EXTIPODECL = readString(dis);

					this.EXNUMERODECLARACAO = readString(dis);

					this.EXDATADECLARACAO = readDate(dis);

					this.EXDATAENTRADA = readDate(dis);

					this.EXVALORTOTAL = readString(dis);

					this.EXDATAEMBARQUE = readDate(dis);

					this.EXNUMEROCONHECIMENTO = readString(dis);

					this.EXDATACONHECIMENTO = readString(dis);

					this.EXTIPOCONHECIMENTO = readString(dis);

					this.EXNOMEPAIS = readString(dis);

					this.EXCODIGOPAIS = readString(dis);

					this.EXDATASISCOMEX = readDate(dis);

					this.EXDATADESEMBARACO = readDate(dis);

					this.EXVALORMERCADORIAS = readString(dis);

					this.EXVALORPIS = readString(dis);

					this.EXVALORCOFINS = readString(dis);

					this.EXVALORADUANA = readString(dis);

					this.EXVALORADUANAICMS = readString(dis);

					this.EXVALORIOF = readString(dis);

					this.EXVALORIMPOSTOIMPORT = readString(dis);

					this.EXVALORBASEIPI = readString(dis);

					this.EXVALORIPI = readString(dis);

					this.EXVALORBASEICM = readString(dis);

					this.EXVALORICM = readString(dis);

					this.EXLOCDESEMB = readString(dis);

					this.EXUFDESEMB = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.EXCODIGOEMPRESA, dos);

				// String

				writeString(this.EXMODELODOCUMENTO, dos);

				// String

				writeString(this.EXSERIEDOCUMENTO, dos);

				// String

				writeString(this.EXNUMERODOCUMENTO, dos);

				// java.util.Date

				writeDate(this.EXDATAEMISSAO, dos);

				// String

				writeString(this.EXCFOP, dos);

				// String

				writeString(this.EXCODIGOFORNECEDOR, dos);

				// String

				writeString(this.EXTIPODECL, dos);

				// String

				writeString(this.EXNUMERODECLARACAO, dos);

				// java.util.Date

				writeDate(this.EXDATADECLARACAO, dos);

				// java.util.Date

				writeDate(this.EXDATAENTRADA, dos);

				// String

				writeString(this.EXVALORTOTAL, dos);

				// java.util.Date

				writeDate(this.EXDATAEMBARQUE, dos);

				// String

				writeString(this.EXNUMEROCONHECIMENTO, dos);

				// String

				writeString(this.EXDATACONHECIMENTO, dos);

				// String

				writeString(this.EXTIPOCONHECIMENTO, dos);

				// String

				writeString(this.EXNOMEPAIS, dos);

				// String

				writeString(this.EXCODIGOPAIS, dos);

				// java.util.Date

				writeDate(this.EXDATASISCOMEX, dos);

				// java.util.Date

				writeDate(this.EXDATADESEMBARACO, dos);

				// String

				writeString(this.EXVALORMERCADORIAS, dos);

				// String

				writeString(this.EXVALORPIS, dos);

				// String

				writeString(this.EXVALORCOFINS, dos);

				// String

				writeString(this.EXVALORADUANA, dos);

				// String

				writeString(this.EXVALORADUANAICMS, dos);

				// String

				writeString(this.EXVALORIOF, dos);

				// String

				writeString(this.EXVALORIMPOSTOIMPORT, dos);

				// String

				writeString(this.EXVALORBASEIPI, dos);

				// String

				writeString(this.EXVALORIPI, dos);

				// String

				writeString(this.EXVALORBASEICM, dos);

				// String

				writeString(this.EXVALORICM, dos);

				// String

				writeString(this.EXLOCDESEMB, dos);

				// String

				writeString(this.EXUFDESEMB, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("EXCODIGOEMPRESA=" + EXCODIGOEMPRESA);
			sb.append(",EXMODELODOCUMENTO=" + EXMODELODOCUMENTO);
			sb.append(",EXSERIEDOCUMENTO=" + EXSERIEDOCUMENTO);
			sb.append(",EXNUMERODOCUMENTO=" + EXNUMERODOCUMENTO);
			sb.append(",EXDATAEMISSAO=" + String.valueOf(EXDATAEMISSAO));
			sb.append(",EXCFOP=" + EXCFOP);
			sb.append(",EXCODIGOFORNECEDOR=" + EXCODIGOFORNECEDOR);
			sb.append(",EXTIPODECL=" + EXTIPODECL);
			sb.append(",EXNUMERODECLARACAO=" + EXNUMERODECLARACAO);
			sb.append(",EXDATADECLARACAO=" + String.valueOf(EXDATADECLARACAO));
			sb.append(",EXDATAENTRADA=" + String.valueOf(EXDATAENTRADA));
			sb.append(",EXVALORTOTAL=" + EXVALORTOTAL);
			sb.append(",EXDATAEMBARQUE=" + String.valueOf(EXDATAEMBARQUE));
			sb.append(",EXNUMEROCONHECIMENTO=" + EXNUMEROCONHECIMENTO);
			sb.append(",EXDATACONHECIMENTO=" + EXDATACONHECIMENTO);
			sb.append(",EXTIPOCONHECIMENTO=" + EXTIPOCONHECIMENTO);
			sb.append(",EXNOMEPAIS=" + EXNOMEPAIS);
			sb.append(",EXCODIGOPAIS=" + EXCODIGOPAIS);
			sb.append(",EXDATASISCOMEX=" + String.valueOf(EXDATASISCOMEX));
			sb.append(",EXDATADESEMBARACO=" + String.valueOf(EXDATADESEMBARACO));
			sb.append(",EXVALORMERCADORIAS=" + EXVALORMERCADORIAS);
			sb.append(",EXVALORPIS=" + EXVALORPIS);
			sb.append(",EXVALORCOFINS=" + EXVALORCOFINS);
			sb.append(",EXVALORADUANA=" + EXVALORADUANA);
			sb.append(",EXVALORADUANAICMS=" + EXVALORADUANAICMS);
			sb.append(",EXVALORIOF=" + EXVALORIOF);
			sb.append(",EXVALORIMPOSTOIMPORT=" + EXVALORIMPOSTOIMPORT);
			sb.append(",EXVALORBASEIPI=" + EXVALORBASEIPI);
			sb.append(",EXVALORIPI=" + EXVALORIPI);
			sb.append(",EXVALORBASEICM=" + EXVALORBASEICM);
			sb.append(",EXVALORICM=" + EXVALORICM);
			sb.append(",EXLOCDESEMB=" + EXLOCDESEMB);
			sb.append(",EXUFDESEMB=" + EXUFDESEMB);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(),
						object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row1Struct implements
			routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_client_conn_ceximport = new byte[0];
		static byte[] commonByteArray_client_conn_ceximport = new byte[0];

		public String EXCODIGOEMPRESA;

		public String getEXCODIGOEMPRESA() {
			return this.EXCODIGOEMPRESA;
		}

		public String EXMODELODOCUMENTO;

		public String getEXMODELODOCUMENTO() {
			return this.EXMODELODOCUMENTO;
		}

		public String EXSERIEDOCUMENTO;

		public String getEXSERIEDOCUMENTO() {
			return this.EXSERIEDOCUMENTO;
		}

		public String EXNUMERODOCUMENTO;

		public String getEXNUMERODOCUMENTO() {
			return this.EXNUMERODOCUMENTO;
		}

		public java.util.Date EXDATAEMISSAO;

		public java.util.Date getEXDATAEMISSAO() {
			return this.EXDATAEMISSAO;
		}

		public String EXCFOP;

		public String getEXCFOP() {
			return this.EXCFOP;
		}

		public String EXCODIGOFORNECEDOR;

		public String getEXCODIGOFORNECEDOR() {
			return this.EXCODIGOFORNECEDOR;
		}

		public String EXTIPODECL;

		public String getEXTIPODECL() {
			return this.EXTIPODECL;
		}

		public String EXNUMERODECLARACAO;

		public String getEXNUMERODECLARACAO() {
			return this.EXNUMERODECLARACAO;
		}

		public java.util.Date EXDATADECLARACAO;

		public java.util.Date getEXDATADECLARACAO() {
			return this.EXDATADECLARACAO;
		}

		public java.util.Date EXDATAENTRADA;

		public java.util.Date getEXDATAENTRADA() {
			return this.EXDATAENTRADA;
		}

		public String EXVALORTOTAL;

		public String getEXVALORTOTAL() {
			return this.EXVALORTOTAL;
		}

		public java.util.Date EXDATAEMBARQUE;

		public java.util.Date getEXDATAEMBARQUE() {
			return this.EXDATAEMBARQUE;
		}

		public String EXNUMEROCONHECIMENTO;

		public String getEXNUMEROCONHECIMENTO() {
			return this.EXNUMEROCONHECIMENTO;
		}

		public String EXDATACONHECIMENTO;

		public String getEXDATACONHECIMENTO() {
			return this.EXDATACONHECIMENTO;
		}

		public String EXTIPOCONHECIMENTO;

		public String getEXTIPOCONHECIMENTO() {
			return this.EXTIPOCONHECIMENTO;
		}

		public String EXNOMEPAIS;

		public String getEXNOMEPAIS() {
			return this.EXNOMEPAIS;
		}

		public String EXCODIGOPAIS;

		public String getEXCODIGOPAIS() {
			return this.EXCODIGOPAIS;
		}

		public java.util.Date EXDATASISCOMEX;

		public java.util.Date getEXDATASISCOMEX() {
			return this.EXDATASISCOMEX;
		}

		public java.util.Date EXDATADESEMBARACO;

		public java.util.Date getEXDATADESEMBARACO() {
			return this.EXDATADESEMBARACO;
		}

		public String EXVALORMERCADORIAS;

		public String getEXVALORMERCADORIAS() {
			return this.EXVALORMERCADORIAS;
		}

		public String EXVALORPIS;

		public String getEXVALORPIS() {
			return this.EXVALORPIS;
		}

		public String EXVALORCOFINS;

		public String getEXVALORCOFINS() {
			return this.EXVALORCOFINS;
		}

		public String EXVALORADUANA;

		public String getEXVALORADUANA() {
			return this.EXVALORADUANA;
		}

		public String EXVALORADUANAICMS;

		public String getEXVALORADUANAICMS() {
			return this.EXVALORADUANAICMS;
		}

		public String EXVALORIOF;

		public String getEXVALORIOF() {
			return this.EXVALORIOF;
		}

		public String EXVALORIMPOSTOIMPORT;

		public String getEXVALORIMPOSTOIMPORT() {
			return this.EXVALORIMPOSTOIMPORT;
		}

		public String EXVALORBASEIPI;

		public String getEXVALORBASEIPI() {
			return this.EXVALORBASEIPI;
		}

		public String EXVALORIPI;

		public String getEXVALORIPI() {
			return this.EXVALORIPI;
		}

		public String EXVALORBASEICM;

		public String getEXVALORBASEICM() {
			return this.EXVALORBASEICM;
		}

		public String EXVALORICM;

		public String getEXVALORICM() {
			return this.EXVALORICM;
		}

		public String EXLOCDESEMB;

		public String getEXLOCDESEMB() {
			return this.EXLOCDESEMB;
		}

		public String EXUFDESEMB;

		public String getEXUFDESEMB() {
			return this.EXUFDESEMB;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_client_conn_ceximport.length) {
					if (length < 1024
							&& commonByteArray_client_conn_ceximport.length == 0) {
						commonByteArray_client_conn_ceximport = new byte[1024];
					} else {
						commonByteArray_client_conn_ceximport = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_client_conn_ceximport, 0,
						length);
				strReturn = new String(
						commonByteArray_client_conn_ceximport, 0, length,
						utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos)
				throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis)
				throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos)
				throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_client_conn_ceximport) {

				try {

					int length = 0;

					this.EXCODIGOEMPRESA = readString(dis);

					this.EXMODELODOCUMENTO = readString(dis);

					this.EXSERIEDOCUMENTO = readString(dis);

					this.EXNUMERODOCUMENTO = readString(dis);

					this.EXDATAEMISSAO = readDate(dis);

					this.EXCFOP = readString(dis);

					this.EXCODIGOFORNECEDOR = readString(dis);

					this.EXTIPODECL = readString(dis);

					this.EXNUMERODECLARACAO = readString(dis);

					this.EXDATADECLARACAO = readDate(dis);

					this.EXDATAENTRADA = readDate(dis);

					this.EXVALORTOTAL = readString(dis);

					this.EXDATAEMBARQUE = readDate(dis);

					this.EXNUMEROCONHECIMENTO = readString(dis);

					this.EXDATACONHECIMENTO = readString(dis);

					this.EXTIPOCONHECIMENTO = readString(dis);

					this.EXNOMEPAIS = readString(dis);

					this.EXCODIGOPAIS = readString(dis);

					this.EXDATASISCOMEX = readDate(dis);

					this.EXDATADESEMBARACO = readDate(dis);

					this.EXVALORMERCADORIAS = readString(dis);

					this.EXVALORPIS = readString(dis);

					this.EXVALORCOFINS = readString(dis);

					this.EXVALORADUANA = readString(dis);

					this.EXVALORADUANAICMS = readString(dis);

					this.EXVALORIOF = readString(dis);

					this.EXVALORIMPOSTOIMPORT = readString(dis);

					this.EXVALORBASEIPI = readString(dis);

					this.EXVALORIPI = readString(dis);

					this.EXVALORBASEICM = readString(dis);

					this.EXVALORICM = readString(dis);

					this.EXLOCDESEMB = readString(dis);

					this.EXUFDESEMB = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.EXCODIGOEMPRESA, dos);

				// String

				writeString(this.EXMODELODOCUMENTO, dos);

				// String

				writeString(this.EXSERIEDOCUMENTO, dos);

				// String

				writeString(this.EXNUMERODOCUMENTO, dos);

				// java.util.Date

				writeDate(this.EXDATAEMISSAO, dos);

				// String

				writeString(this.EXCFOP, dos);

				// String

				writeString(this.EXCODIGOFORNECEDOR, dos);

				// String

				writeString(this.EXTIPODECL, dos);

				// String

				writeString(this.EXNUMERODECLARACAO, dos);

				// java.util.Date

				writeDate(this.EXDATADECLARACAO, dos);

				// java.util.Date

				writeDate(this.EXDATAENTRADA, dos);

				// String

				writeString(this.EXVALORTOTAL, dos);

				// java.util.Date

				writeDate(this.EXDATAEMBARQUE, dos);

				// String

				writeString(this.EXNUMEROCONHECIMENTO, dos);

				// String

				writeString(this.EXDATACONHECIMENTO, dos);

				// String

				writeString(this.EXTIPOCONHECIMENTO, dos);

				// String

				writeString(this.EXNOMEPAIS, dos);

				// String

				writeString(this.EXCODIGOPAIS, dos);

				// java.util.Date

				writeDate(this.EXDATASISCOMEX, dos);

				// java.util.Date

				writeDate(this.EXDATADESEMBARACO, dos);

				// String

				writeString(this.EXVALORMERCADORIAS, dos);

				// String

				writeString(this.EXVALORPIS, dos);

				// String

				writeString(this.EXVALORCOFINS, dos);

				// String

				writeString(this.EXVALORADUANA, dos);

				// String

				writeString(this.EXVALORADUANAICMS, dos);

				// String

				writeString(this.EXVALORIOF, dos);

				// String

				writeString(this.EXVALORIMPOSTOIMPORT, dos);

				// String

				writeString(this.EXVALORBASEIPI, dos);

				// String

				writeString(this.EXVALORIPI, dos);

				// String

				writeString(this.EXVALORBASEICM, dos);

				// String

				writeString(this.EXVALORICM, dos);

				// String

				writeString(this.EXLOCDESEMB, dos);

				// String

				writeString(this.EXUFDESEMB, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("EXCODIGOEMPRESA=" + EXCODIGOEMPRESA);
			sb.append(",EXMODELODOCUMENTO=" + EXMODELODOCUMENTO);
			sb.append(",EXSERIEDOCUMENTO=" + EXSERIEDOCUMENTO);
			sb.append(",EXNUMERODOCUMENTO=" + EXNUMERODOCUMENTO);
			sb.append(",EXDATAEMISSAO=" + String.valueOf(EXDATAEMISSAO));
			sb.append(",EXCFOP=" + EXCFOP);
			sb.append(",EXCODIGOFORNECEDOR=" + EXCODIGOFORNECEDOR);
			sb.append(",EXTIPODECL=" + EXTIPODECL);
			sb.append(",EXNUMERODECLARACAO=" + EXNUMERODECLARACAO);
			sb.append(",EXDATADECLARACAO=" + String.valueOf(EXDATADECLARACAO));
			sb.append(",EXDATAENTRADA=" + String.valueOf(EXDATAENTRADA));
			sb.append(",EXVALORTOTAL=" + EXVALORTOTAL);
			sb.append(",EXDATAEMBARQUE=" + String.valueOf(EXDATAEMBARQUE));
			sb.append(",EXNUMEROCONHECIMENTO=" + EXNUMEROCONHECIMENTO);
			sb.append(",EXDATACONHECIMENTO=" + EXDATACONHECIMENTO);
			sb.append(",EXTIPOCONHECIMENTO=" + EXTIPOCONHECIMENTO);
			sb.append(",EXNOMEPAIS=" + EXNOMEPAIS);
			sb.append(",EXCODIGOPAIS=" + EXCODIGOPAIS);
			sb.append(",EXDATASISCOMEX=" + String.valueOf(EXDATASISCOMEX));
			sb.append(",EXDATADESEMBARACO=" + String.valueOf(EXDATADESEMBARACO));
			sb.append(",EXVALORMERCADORIAS=" + EXVALORMERCADORIAS);
			sb.append(",EXVALORPIS=" + EXVALORPIS);
			sb.append(",EXVALORCOFINS=" + EXVALORCOFINS);
			sb.append(",EXVALORADUANA=" + EXVALORADUANA);
			sb.append(",EXVALORADUANAICMS=" + EXVALORADUANAICMS);
			sb.append(",EXVALORIOF=" + EXVALORIOF);
			sb.append(",EXVALORIMPOSTOIMPORT=" + EXVALORIMPOSTOIMPORT);
			sb.append(",EXVALORBASEIPI=" + EXVALORBASEIPI);
			sb.append(",EXVALORIPI=" + EXVALORIPI);
			sb.append(",EXVALORBASEICM=" + EXVALORBASEICM);
			sb.append(",EXVALORICM=" + EXVALORICM);
			sb.append(",EXLOCDESEMB=" + EXLOCDESEMB);
			sb.append(",EXUFDESEMB=" + EXUFDESEMB);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(),
						object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class outSMARTStruct implements
			routines.system.IPersistableRow<outSMARTStruct> {
		final static byte[] commonByteArrayLock_client_conn_ceximport = new byte[0];
		static byte[] commonByteArray_client_conn_ceximport = new byte[0];

		public String EXCODIGOEMPRESA;

		public String getEXCODIGOEMPRESA() {
			return this.EXCODIGOEMPRESA;
		}

		public String EXMODELODOCUMENTO;

		public String getEXMODELODOCUMENTO() {
			return this.EXMODELODOCUMENTO;
		}

		public String EXSERIEDOCUMENTO;

		public String getEXSERIEDOCUMENTO() {
			return this.EXSERIEDOCUMENTO;
		}

		public String EXNUMERODOCUMENTO;

		public String getEXNUMERODOCUMENTO() {
			return this.EXNUMERODOCUMENTO;
		}

		public java.util.Date EXDATAEMISSAO;

		public java.util.Date getEXDATAEMISSAO() {
			return this.EXDATAEMISSAO;
		}

		public String EXCFOP;

		public String getEXCFOP() {
			return this.EXCFOP;
		}

		public String EXCODIGOFORNECEDOR;

		public String getEXCODIGOFORNECEDOR() {
			return this.EXCODIGOFORNECEDOR;
		}

		public String EXTIPODECL;

		public String getEXTIPODECL() {
			return this.EXTIPODECL;
		}

		public String EXNUMERODECLARACAO;

		public String getEXNUMERODECLARACAO() {
			return this.EXNUMERODECLARACAO;
		}

		public java.util.Date EXDATADECLARACAO;

		public java.util.Date getEXDATADECLARACAO() {
			return this.EXDATADECLARACAO;
		}

		public java.util.Date EXDATAENTRADA;

		public java.util.Date getEXDATAENTRADA() {
			return this.EXDATAENTRADA;
		}

		public String EXVALORTOTAL;

		public String getEXVALORTOTAL() {
			return this.EXVALORTOTAL;
		}

		public java.util.Date EXDATAEMBARQUE;

		public java.util.Date getEXDATAEMBARQUE() {
			return this.EXDATAEMBARQUE;
		}

		public String EXNUMEROCONHECIMENTO;

		public String getEXNUMEROCONHECIMENTO() {
			return this.EXNUMEROCONHECIMENTO;
		}

		public String EXDATACONHECIMENTO;

		public String getEXDATACONHECIMENTO() {
			return this.EXDATACONHECIMENTO;
		}

		public String EXTIPOCONHECIMENTO;

		public String getEXTIPOCONHECIMENTO() {
			return this.EXTIPOCONHECIMENTO;
		}

		public String EXNOMEPAIS;

		public String getEXNOMEPAIS() {
			return this.EXNOMEPAIS;
		}

		public String EXCODIGOPAIS;

		public String getEXCODIGOPAIS() {
			return this.EXCODIGOPAIS;
		}

		public java.util.Date EXDATASISCOMEX;

		public java.util.Date getEXDATASISCOMEX() {
			return this.EXDATASISCOMEX;
		}

		public java.util.Date EXDATADESEMBARACO;

		public java.util.Date getEXDATADESEMBARACO() {
			return this.EXDATADESEMBARACO;
		}

		public String EXVALORMERCADORIAS;

		public String getEXVALORMERCADORIAS() {
			return this.EXVALORMERCADORIAS;
		}

		public String EXVALORPIS;

		public String getEXVALORPIS() {
			return this.EXVALORPIS;
		}

		public String EXVALORCOFINS;

		public String getEXVALORCOFINS() {
			return this.EXVALORCOFINS;
		}

		public String EXVALORADUANA;

		public String getEXVALORADUANA() {
			return this.EXVALORADUANA;
		}

		public String EXVALORADUANAICMS;

		public String getEXVALORADUANAICMS() {
			return this.EXVALORADUANAICMS;
		}

		public String EXVALORIOF;

		public String getEXVALORIOF() {
			return this.EXVALORIOF;
		}

		public String EXVALORIMPOSTOIMPORT;

		public String getEXVALORIMPOSTOIMPORT() {
			return this.EXVALORIMPOSTOIMPORT;
		}

		public String EXVALORBASEIPI;

		public String getEXVALORBASEIPI() {
			return this.EXVALORBASEIPI;
		}

		public String EXVALORIPI;

		public String getEXVALORIPI() {
			return this.EXVALORIPI;
		}

		public String EXVALORBASEICM;

		public String getEXVALORBASEICM() {
			return this.EXVALORBASEICM;
		}

		public String EXVALORICM;

		public String getEXVALORICM() {
			return this.EXVALORICM;
		}

		public String EXLOCDESEMB;

		public String getEXLOCDESEMB() {
			return this.EXLOCDESEMB;
		}

		public String EXUFDESEMB;

		public String getEXUFDESEMB() {
			return this.EXUFDESEMB;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_client_conn_ceximport.length) {
					if (length < 1024
							&& commonByteArray_client_conn_ceximport.length == 0) {
						commonByteArray_client_conn_ceximport = new byte[1024];
					} else {
						commonByteArray_client_conn_ceximport = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_client_conn_ceximport, 0,
						length);
				strReturn = new String(
						commonByteArray_client_conn_ceximport, 0, length,
						utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos)
				throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis)
				throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos)
				throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_client_conn_ceximport) {

				try {

					int length = 0;

					this.EXCODIGOEMPRESA = readString(dis);

					this.EXMODELODOCUMENTO = readString(dis);

					this.EXSERIEDOCUMENTO = readString(dis);

					this.EXNUMERODOCUMENTO = readString(dis);

					this.EXDATAEMISSAO = readDate(dis);

					this.EXCFOP = readString(dis);

					this.EXCODIGOFORNECEDOR = readString(dis);

					this.EXTIPODECL = readString(dis);

					this.EXNUMERODECLARACAO = readString(dis);

					this.EXDATADECLARACAO = readDate(dis);

					this.EXDATAENTRADA = readDate(dis);

					this.EXVALORTOTAL = readString(dis);

					this.EXDATAEMBARQUE = readDate(dis);

					this.EXNUMEROCONHECIMENTO = readString(dis);

					this.EXDATACONHECIMENTO = readString(dis);

					this.EXTIPOCONHECIMENTO = readString(dis);

					this.EXNOMEPAIS = readString(dis);

					this.EXCODIGOPAIS = readString(dis);

					this.EXDATASISCOMEX = readDate(dis);

					this.EXDATADESEMBARACO = readDate(dis);

					this.EXVALORMERCADORIAS = readString(dis);

					this.EXVALORPIS = readString(dis);

					this.EXVALORCOFINS = readString(dis);

					this.EXVALORADUANA = readString(dis);

					this.EXVALORADUANAICMS = readString(dis);

					this.EXVALORIOF = readString(dis);

					this.EXVALORIMPOSTOIMPORT = readString(dis);

					this.EXVALORBASEIPI = readString(dis);

					this.EXVALORIPI = readString(dis);

					this.EXVALORBASEICM = readString(dis);

					this.EXVALORICM = readString(dis);

					this.EXLOCDESEMB = readString(dis);

					this.EXUFDESEMB = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.EXCODIGOEMPRESA, dos);

				// String

				writeString(this.EXMODELODOCUMENTO, dos);

				// String

				writeString(this.EXSERIEDOCUMENTO, dos);

				// String

				writeString(this.EXNUMERODOCUMENTO, dos);

				// java.util.Date

				writeDate(this.EXDATAEMISSAO, dos);

				// String

				writeString(this.EXCFOP, dos);

				// String

				writeString(this.EXCODIGOFORNECEDOR, dos);

				// String

				writeString(this.EXTIPODECL, dos);

				// String

				writeString(this.EXNUMERODECLARACAO, dos);

				// java.util.Date

				writeDate(this.EXDATADECLARACAO, dos);

				// java.util.Date

				writeDate(this.EXDATAENTRADA, dos);

				// String

				writeString(this.EXVALORTOTAL, dos);

				// java.util.Date

				writeDate(this.EXDATAEMBARQUE, dos);

				// String

				writeString(this.EXNUMEROCONHECIMENTO, dos);

				// String

				writeString(this.EXDATACONHECIMENTO, dos);

				// String

				writeString(this.EXTIPOCONHECIMENTO, dos);

				// String

				writeString(this.EXNOMEPAIS, dos);

				// String

				writeString(this.EXCODIGOPAIS, dos);

				// java.util.Date

				writeDate(this.EXDATASISCOMEX, dos);

				// java.util.Date

				writeDate(this.EXDATADESEMBARACO, dos);

				// String

				writeString(this.EXVALORMERCADORIAS, dos);

				// String

				writeString(this.EXVALORPIS, dos);

				// String

				writeString(this.EXVALORCOFINS, dos);

				// String

				writeString(this.EXVALORADUANA, dos);

				// String

				writeString(this.EXVALORADUANAICMS, dos);

				// String

				writeString(this.EXVALORIOF, dos);

				// String

				writeString(this.EXVALORIMPOSTOIMPORT, dos);

				// String

				writeString(this.EXVALORBASEIPI, dos);

				// String

				writeString(this.EXVALORIPI, dos);

				// String

				writeString(this.EXVALORBASEICM, dos);

				// String

				writeString(this.EXVALORICM, dos);

				// String

				writeString(this.EXLOCDESEMB, dos);

				// String

				writeString(this.EXUFDESEMB, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("EXCODIGOEMPRESA=" + EXCODIGOEMPRESA);
			sb.append(",EXMODELODOCUMENTO=" + EXMODELODOCUMENTO);
			sb.append(",EXSERIEDOCUMENTO=" + EXSERIEDOCUMENTO);
			sb.append(",EXNUMERODOCUMENTO=" + EXNUMERODOCUMENTO);
			sb.append(",EXDATAEMISSAO=" + String.valueOf(EXDATAEMISSAO));
			sb.append(",EXCFOP=" + EXCFOP);
			sb.append(",EXCODIGOFORNECEDOR=" + EXCODIGOFORNECEDOR);
			sb.append(",EXTIPODECL=" + EXTIPODECL);
			sb.append(",EXNUMERODECLARACAO=" + EXNUMERODECLARACAO);
			sb.append(",EXDATADECLARACAO=" + String.valueOf(EXDATADECLARACAO));
			sb.append(",EXDATAENTRADA=" + String.valueOf(EXDATAENTRADA));
			sb.append(",EXVALORTOTAL=" + EXVALORTOTAL);
			sb.append(",EXDATAEMBARQUE=" + String.valueOf(EXDATAEMBARQUE));
			sb.append(",EXNUMEROCONHECIMENTO=" + EXNUMEROCONHECIMENTO);
			sb.append(",EXDATACONHECIMENTO=" + EXDATACONHECIMENTO);
			sb.append(",EXTIPOCONHECIMENTO=" + EXTIPOCONHECIMENTO);
			sb.append(",EXNOMEPAIS=" + EXNOMEPAIS);
			sb.append(",EXCODIGOPAIS=" + EXCODIGOPAIS);
			sb.append(",EXDATASISCOMEX=" + String.valueOf(EXDATASISCOMEX));
			sb.append(",EXDATADESEMBARACO=" + String.valueOf(EXDATADESEMBARACO));
			sb.append(",EXVALORMERCADORIAS=" + EXVALORMERCADORIAS);
			sb.append(",EXVALORPIS=" + EXVALORPIS);
			sb.append(",EXVALORCOFINS=" + EXVALORCOFINS);
			sb.append(",EXVALORADUANA=" + EXVALORADUANA);
			sb.append(",EXVALORADUANAICMS=" + EXVALORADUANAICMS);
			sb.append(",EXVALORIOF=" + EXVALORIOF);
			sb.append(",EXVALORIMPOSTOIMPORT=" + EXVALORIMPOSTOIMPORT);
			sb.append(",EXVALORBASEIPI=" + EXVALORBASEIPI);
			sb.append(",EXVALORIPI=" + EXVALORIPI);
			sb.append(",EXVALORBASEICM=" + EXVALORBASEICM);
			sb.append(",EXVALORICM=" + EXVALORICM);
			sb.append(",EXLOCDESEMB=" + EXLOCDESEMB);
			sb.append(",EXUFDESEMB=" + EXUFDESEMB);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(outSMARTStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(),
						object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class CEXIMPORTStruct implements
			routines.system.IPersistableRow<CEXIMPORTStruct> {
		final static byte[] commonByteArrayLock_client_conn_ceximport = new byte[0];
		static byte[] commonByteArray_client_conn_ceximport = new byte[0];

		public String EXCODIGOEMPRESA;

		public String getEXCODIGOEMPRESA() {
			return this.EXCODIGOEMPRESA;
		}

		public String EXMODELODOCUMENTO;

		public String getEXMODELODOCUMENTO() {
			return this.EXMODELODOCUMENTO;
		}

		public String EXSERIEDOCUMENTO;

		public String getEXSERIEDOCUMENTO() {
			return this.EXSERIEDOCUMENTO;
		}

		public String EXNUMERODOCUMENTO;

		public String getEXNUMERODOCUMENTO() {
			return this.EXNUMERODOCUMENTO;
		}

		public java.util.Date EXDATAEMISSAO;

		public java.util.Date getEXDATAEMISSAO() {
			return this.EXDATAEMISSAO;
		}

		public String EXCFOP;

		public String getEXCFOP() {
			return this.EXCFOP;
		}

		public String EXCODIGOFORNECEDOR;

		public String getEXCODIGOFORNECEDOR() {
			return this.EXCODIGOFORNECEDOR;
		}

		public String EXTIPODECL;

		public String getEXTIPODECL() {
			return this.EXTIPODECL;
		}

		public String EXNUMERODECLARACAO;

		public String getEXNUMERODECLARACAO() {
			return this.EXNUMERODECLARACAO;
		}

		public java.util.Date EXDATADECLARACAO;

		public java.util.Date getEXDATADECLARACAO() {
			return this.EXDATADECLARACAO;
		}

		public java.util.Date EXDATAENTRADA;

		public java.util.Date getEXDATAENTRADA() {
			return this.EXDATAENTRADA;
		}

		public String EXVALORTOTAL;

		public String getEXVALORTOTAL() {
			return this.EXVALORTOTAL;
		}

		public java.util.Date EXDATAEMBARQUE;

		public java.util.Date getEXDATAEMBARQUE() {
			return this.EXDATAEMBARQUE;
		}

		public String EXNUMEROCONHECIMENTO;

		public String getEXNUMEROCONHECIMENTO() {
			return this.EXNUMEROCONHECIMENTO;
		}

		public String EXDATACONHECIMENTO;

		public String getEXDATACONHECIMENTO() {
			return this.EXDATACONHECIMENTO;
		}

		public String EXTIPOCONHECIMENTO;

		public String getEXTIPOCONHECIMENTO() {
			return this.EXTIPOCONHECIMENTO;
		}

		public String EXNOMEPAIS;

		public String getEXNOMEPAIS() {
			return this.EXNOMEPAIS;
		}

		public String EXCODIGOPAIS;

		public String getEXCODIGOPAIS() {
			return this.EXCODIGOPAIS;
		}

		public java.util.Date EXDATASISCOMEX;

		public java.util.Date getEXDATASISCOMEX() {
			return this.EXDATASISCOMEX;
		}

		public java.util.Date EXDATADESEMBARACO;

		public java.util.Date getEXDATADESEMBARACO() {
			return this.EXDATADESEMBARACO;
		}

		public String EXVALORMERCADORIAS;

		public String getEXVALORMERCADORIAS() {
			return this.EXVALORMERCADORIAS;
		}

		public String EXVALORPIS;

		public String getEXVALORPIS() {
			return this.EXVALORPIS;
		}

		public String EXVALORCOFINS;

		public String getEXVALORCOFINS() {
			return this.EXVALORCOFINS;
		}

		public String EXVALORADUANA;

		public String getEXVALORADUANA() {
			return this.EXVALORADUANA;
		}

		public String EXVALORADUANAICMS;

		public String getEXVALORADUANAICMS() {
			return this.EXVALORADUANAICMS;
		}

		public String EXVALORIOF;

		public String getEXVALORIOF() {
			return this.EXVALORIOF;
		}

		public String EXVALORIMPOSTOIMPORT;

		public String getEXVALORIMPOSTOIMPORT() {
			return this.EXVALORIMPOSTOIMPORT;
		}

		public String EXVALORBASEIPI;

		public String getEXVALORBASEIPI() {
			return this.EXVALORBASEIPI;
		}

		public String EXVALORIPI;

		public String getEXVALORIPI() {
			return this.EXVALORIPI;
		}

		public String EXVALORBASEICM;

		public String getEXVALORBASEICM() {
			return this.EXVALORBASEICM;
		}

		public String EXVALORICM;

		public String getEXVALORICM() {
			return this.EXVALORICM;
		}

		public String EXLOCDESEMB;

		public String getEXLOCDESEMB() {
			return this.EXLOCDESEMB;
		}

		public String EXUFDESEMB;

		public String getEXUFDESEMB() {
			return this.EXUFDESEMB;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_client_conn_ceximport.length) {
					if (length < 1024
							&& commonByteArray_client_conn_ceximport.length == 0) {
						commonByteArray_client_conn_ceximport = new byte[1024];
					} else {
						commonByteArray_client_conn_ceximport = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_client_conn_ceximport, 0,
						length);
				strReturn = new String(
						commonByteArray_client_conn_ceximport, 0, length,
						utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos)
				throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis)
				throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos)
				throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_client_conn_ceximport) {

				try {

					int length = 0;

					this.EXCODIGOEMPRESA = readString(dis);

					this.EXMODELODOCUMENTO = readString(dis);

					this.EXSERIEDOCUMENTO = readString(dis);

					this.EXNUMERODOCUMENTO = readString(dis);

					this.EXDATAEMISSAO = readDate(dis);

					this.EXCFOP = readString(dis);

					this.EXCODIGOFORNECEDOR = readString(dis);

					this.EXTIPODECL = readString(dis);

					this.EXNUMERODECLARACAO = readString(dis);

					this.EXDATADECLARACAO = readDate(dis);

					this.EXDATAENTRADA = readDate(dis);

					this.EXVALORTOTAL = readString(dis);

					this.EXDATAEMBARQUE = readDate(dis);

					this.EXNUMEROCONHECIMENTO = readString(dis);

					this.EXDATACONHECIMENTO = readString(dis);

					this.EXTIPOCONHECIMENTO = readString(dis);

					this.EXNOMEPAIS = readString(dis);

					this.EXCODIGOPAIS = readString(dis);

					this.EXDATASISCOMEX = readDate(dis);

					this.EXDATADESEMBARACO = readDate(dis);

					this.EXVALORMERCADORIAS = readString(dis);

					this.EXVALORPIS = readString(dis);

					this.EXVALORCOFINS = readString(dis);

					this.EXVALORADUANA = readString(dis);

					this.EXVALORADUANAICMS = readString(dis);

					this.EXVALORIOF = readString(dis);

					this.EXVALORIMPOSTOIMPORT = readString(dis);

					this.EXVALORBASEIPI = readString(dis);

					this.EXVALORIPI = readString(dis);

					this.EXVALORBASEICM = readString(dis);

					this.EXVALORICM = readString(dis);

					this.EXLOCDESEMB = readString(dis);

					this.EXUFDESEMB = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.EXCODIGOEMPRESA, dos);

				// String

				writeString(this.EXMODELODOCUMENTO, dos);

				// String

				writeString(this.EXSERIEDOCUMENTO, dos);

				// String

				writeString(this.EXNUMERODOCUMENTO, dos);

				// java.util.Date

				writeDate(this.EXDATAEMISSAO, dos);

				// String

				writeString(this.EXCFOP, dos);

				// String

				writeString(this.EXCODIGOFORNECEDOR, dos);

				// String

				writeString(this.EXTIPODECL, dos);

				// String

				writeString(this.EXNUMERODECLARACAO, dos);

				// java.util.Date

				writeDate(this.EXDATADECLARACAO, dos);

				// java.util.Date

				writeDate(this.EXDATAENTRADA, dos);

				// String

				writeString(this.EXVALORTOTAL, dos);

				// java.util.Date

				writeDate(this.EXDATAEMBARQUE, dos);

				// String

				writeString(this.EXNUMEROCONHECIMENTO, dos);

				// String

				writeString(this.EXDATACONHECIMENTO, dos);

				// String

				writeString(this.EXTIPOCONHECIMENTO, dos);

				// String

				writeString(this.EXNOMEPAIS, dos);

				// String

				writeString(this.EXCODIGOPAIS, dos);

				// java.util.Date

				writeDate(this.EXDATASISCOMEX, dos);

				// java.util.Date

				writeDate(this.EXDATADESEMBARACO, dos);

				// String

				writeString(this.EXVALORMERCADORIAS, dos);

				// String

				writeString(this.EXVALORPIS, dos);

				// String

				writeString(this.EXVALORCOFINS, dos);

				// String

				writeString(this.EXVALORADUANA, dos);

				// String

				writeString(this.EXVALORADUANAICMS, dos);

				// String

				writeString(this.EXVALORIOF, dos);

				// String

				writeString(this.EXVALORIMPOSTOIMPORT, dos);

				// String

				writeString(this.EXVALORBASEIPI, dos);

				// String

				writeString(this.EXVALORIPI, dos);

				// String

				writeString(this.EXVALORBASEICM, dos);

				// String

				writeString(this.EXVALORICM, dos);

				// String

				writeString(this.EXLOCDESEMB, dos);

				// String

				writeString(this.EXUFDESEMB, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("EXCODIGOEMPRESA=" + EXCODIGOEMPRESA);
			sb.append(",EXMODELODOCUMENTO=" + EXMODELODOCUMENTO);
			sb.append(",EXSERIEDOCUMENTO=" + EXSERIEDOCUMENTO);
			sb.append(",EXNUMERODOCUMENTO=" + EXNUMERODOCUMENTO);
			sb.append(",EXDATAEMISSAO=" + String.valueOf(EXDATAEMISSAO));
			sb.append(",EXCFOP=" + EXCFOP);
			sb.append(",EXCODIGOFORNECEDOR=" + EXCODIGOFORNECEDOR);
			sb.append(",EXTIPODECL=" + EXTIPODECL);
			sb.append(",EXNUMERODECLARACAO=" + EXNUMERODECLARACAO);
			sb.append(",EXDATADECLARACAO=" + String.valueOf(EXDATADECLARACAO));
			sb.append(",EXDATAENTRADA=" + String.valueOf(EXDATAENTRADA));
			sb.append(",EXVALORTOTAL=" + EXVALORTOTAL);
			sb.append(",EXDATAEMBARQUE=" + String.valueOf(EXDATAEMBARQUE));
			sb.append(",EXNUMEROCONHECIMENTO=" + EXNUMEROCONHECIMENTO);
			sb.append(",EXDATACONHECIMENTO=" + EXDATACONHECIMENTO);
			sb.append(",EXTIPOCONHECIMENTO=" + EXTIPOCONHECIMENTO);
			sb.append(",EXNOMEPAIS=" + EXNOMEPAIS);
			sb.append(",EXCODIGOPAIS=" + EXCODIGOPAIS);
			sb.append(",EXDATASISCOMEX=" + String.valueOf(EXDATASISCOMEX));
			sb.append(",EXDATADESEMBARACO=" + String.valueOf(EXDATADESEMBARACO));
			sb.append(",EXVALORMERCADORIAS=" + EXVALORMERCADORIAS);
			sb.append(",EXVALORPIS=" + EXVALORPIS);
			sb.append(",EXVALORCOFINS=" + EXVALORCOFINS);
			sb.append(",EXVALORADUANA=" + EXVALORADUANA);
			sb.append(",EXVALORADUANAICMS=" + EXVALORADUANAICMS);
			sb.append(",EXVALORIOF=" + EXVALORIOF);
			sb.append(",EXVALORIMPOSTOIMPORT=" + EXVALORIMPOSTOIMPORT);
			sb.append(",EXVALORBASEIPI=" + EXVALORBASEIPI);
			sb.append(",EXVALORIPI=" + EXVALORIPI);
			sb.append(",EXVALORBASEICM=" + EXVALORBASEICM);
			sb.append(",EXVALORICM=" + EXVALORICM);
			sb.append(",EXLOCDESEMB=" + EXLOCDESEMB);
			sb.append(",EXUFDESEMB=" + EXUFDESEMB);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(CEXIMPORTStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(),
						object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tSAPInput_1Process(final java.util.Map<String, Object> globalMap)
			throws TalendException {
		globalMap.put("tSAPInput_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {

			String currentMethodName = new java.lang.Exception()
					.getStackTrace()[0].getMethodName();
			boolean resumeIt = currentMethodName.equals(resumeEntryMethodName);
			if (resumeEntryMethodName == null || resumeIt || globalResumeTicket) {// start
																					// the
																					// resume
				globalResumeTicket = true;

				CEXIMPORTStruct CEXIMPORT = new CEXIMPORTStruct();
				outSMARTStruct outSMART = new outSMARTStruct();
				row1Struct row1 = new row1Struct();
				row2Struct row2 = new row2Struct();

				/**
				 * [tMSSqlOutput_1 begin ] start
				 */

				ok_Hash.put("tMSSqlOutput_1", false);
				start_Hash.put("tMSSqlOutput_1", System.currentTimeMillis());

				currentComponent = "tMSSqlOutput_1";

				int tos_count_tMSSqlOutput_1 = 0;

				int nb_line_tMSSqlOutput_1 = 0;
				int nb_line_update_tMSSqlOutput_1 = 0;
				int nb_line_inserted_tMSSqlOutput_1 = 0;
				int nb_line_deleted_tMSSqlOutput_1 = 0;
				int nb_line_rejected_tMSSqlOutput_1 = 0;

				int deletedCount_tMSSqlOutput_1 = 0;
				int updatedCount_tMSSqlOutput_1 = 0;
				int insertedCount_tMSSqlOutput_1 = 0;
				int rejectedCount_tMSSqlOutput_1 = 0;
				String dbschema_tMSSqlOutput_1 = null;
				String tableName_tMSSqlOutput_1 = null;
				boolean whetherReject_tMSSqlOutput_1 = false;

				java.util.Calendar calendar_tMSSqlOutput_1 = java.util.Calendar
						.getInstance();
				long year1_tMSSqlOutput_1 = TalendDate.parseDate("yyyy-MM-dd",
						"0001-01-01").getTime();
				long year2_tMSSqlOutput_1 = TalendDate.parseDate("yyyy-MM-dd",
						"1753-01-01").getTime();
				long year10000_tMSSqlOutput_1 = TalendDate.parseDate(
						"yyyy-MM-dd HH:mm:ss", "9999-12-31 24:00:00").getTime();
				long date_tMSSqlOutput_1;

				java.util.Calendar calendar_datetimeoffset_tMSSqlOutput_1 = java.util.Calendar
						.getInstance(java.util.TimeZone.getTimeZone("UTC"));

				java.sql.Connection conn_tMSSqlOutput_1 = null;
				String dbUser_tMSSqlOutput_1 = null;
				dbschema_tMSSqlOutput_1 = context.schema;
				String driverClass_tMSSqlOutput_1 = "net.sourceforge.jtds.jdbc.Driver";

				java.lang.Class.forName(driverClass_tMSSqlOutput_1);
				String port_tMSSqlOutput_1 = context.port;
				String dbname_tMSSqlOutput_1 = context.database;
				String url_tMSSqlOutput_1 = "jdbc:jtds:sqlserver://"
						+ context.host;
				if (!"".equals(port_tMSSqlOutput_1)) {
					url_tMSSqlOutput_1 += ":" + context.port;
				}
				if (!"".equals(dbname_tMSSqlOutput_1)) {
					url_tMSSqlOutput_1 += "//" + context.database;
				}
				url_tMSSqlOutput_1 += ";appName=" + projectName + ";" + "";
				dbUser_tMSSqlOutput_1 = context.username;

				final String decryptedPassword_tMSSqlOutput_1 = context.pass;

				String dbPwd_tMSSqlOutput_1 = decryptedPassword_tMSSqlOutput_1;
				conn_tMSSqlOutput_1 = java.sql.DriverManager.getConnection(
						url_tMSSqlOutput_1, dbUser_tMSSqlOutput_1,
						dbPwd_tMSSqlOutput_1);

				resourceMap.put("conn_tMSSqlOutput_1", conn_tMSSqlOutput_1);

				conn_tMSSqlOutput_1.setAutoCommit(false);
				int commitEvery_tMSSqlOutput_1 = 10000;
				int commitCounter_tMSSqlOutput_1 = 0;

				int batchSize_tMSSqlOutput_1 = 10000;
				int batchSizeCounter_tMSSqlOutput_1 = 0;

				if (dbschema_tMSSqlOutput_1 == null
						|| dbschema_tMSSqlOutput_1.trim().length() == 0) {
					tableName_tMSSqlOutput_1 = context.tabela;
				} else {
					tableName_tMSSqlOutput_1 = dbschema_tMSSqlOutput_1 + "].["
							+ context.tabela;
				}
				int count_tMSSqlOutput_1 = 0;

				java.sql.Statement stmtTruncCount_tMSSqlOutput_1 = conn_tMSSqlOutput_1
						.createStatement();
				java.sql.ResultSet rsTruncCount_tMSSqlOutput_1 = stmtTruncCount_tMSSqlOutput_1
						.executeQuery("SELECT COUNT(1) FROM ["
								+ tableName_tMSSqlOutput_1 + "]");
				int rsTruncCountNumber_tMSSqlOutput_1 = 0;
				if (rsTruncCount_tMSSqlOutput_1.next()) {
					rsTruncCountNumber_tMSSqlOutput_1 = rsTruncCount_tMSSqlOutput_1
							.getInt(1);
				}
				rsTruncCount_tMSSqlOutput_1.close();
				stmtTruncCount_tMSSqlOutput_1.close();
				java.sql.Statement stmtTrunc_tMSSqlOutput_1 = conn_tMSSqlOutput_1
						.createStatement();
				stmtTrunc_tMSSqlOutput_1.executeUpdate("TRUNCATE TABLE ["
						+ tableName_tMSSqlOutput_1 + "]");
				deletedCount_tMSSqlOutput_1 += rsTruncCountNumber_tMSSqlOutput_1;
				stmtTrunc_tMSSqlOutput_1.close();
				String insert_tMSSqlOutput_1 = "INSERT INTO ["
						+ tableName_tMSSqlOutput_1
						+ "] ([EXCODIGOEMPRESA],[EXMODELODOCUMENTO],[EXSERIEDOCUMENTO],[EXNUMERODOCUMENTO],[EXDATAEMISSAO],[EXCFOP],[EXCODIGOFORNECEDOR],[EXTIPODECL],[EXNUMERODECLARACAO],[EXDATADECLARACAO],[EXDATAENTRADA],[EXVALORTOTAL],[EXDATAEMBARQUE],[EXNUMEROCONHECIMENTO],[EXDATACONHECIMENTO],[EXTIPOCONHECIMENTO],[EXNOMEPAIS],[EXCODIGOPAIS],[EXDATASISCOMEX],[EXDATADESEMBARACO],[EXVALORMERCADORIAS],[EXVALORPIS],[EXVALORCOFINS],[EXVALORADUANA],[EXVALORADUANAICMS],[EXVALORIOF],[EXVALORIMPOSTOIMPORT],[EXVALORBASEIPI],[EXVALORIPI],[EXVALORBASEICM],[EXVALORICM],[EXLOCDESEMB],[EXUFDESEMB]) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				java.sql.PreparedStatement pstmt_tMSSqlOutput_1 = conn_tMSSqlOutput_1
						.prepareStatement(insert_tMSSqlOutput_1);

				/**
				 * [tMSSqlOutput_1 begin ] stop
				 */

				/**
				 * [tReplace_1 begin ] start
				 */

				ok_Hash.put("tReplace_1", false);
				start_Hash.put("tReplace_1", System.currentTimeMillis());

				currentComponent = "tReplace_1";

				int tos_count_tReplace_1 = 0;

				int nb_line_tReplace_1 = 0;

				/**
				 * [tReplace_1 begin ] stop
				 */

				/**
				 * [tReplaceNull_1 begin ] start
				 */

				ok_Hash.put("tReplaceNull_1", false);
				start_Hash.put("tReplaceNull_1", System.currentTimeMillis());

				currentComponent = "tReplaceNull_1";

				int tos_count_tReplaceNull_1 = 0;

				// helper to transfer values with null check
				final class Helper_tReplaceNull_1 {

					public Object coalesce(Object value,
							Object columnDefaultValue,
							Object schemaDefaultValue, Object typeDefaultValue) {
						if (value != null) {
							return value;
						} else if (columnDefaultValue != null) {
							return columnDefaultValue;
						} else if (schemaDefaultValue != null) {
							return schemaDefaultValue;
						} else {
							return typeDefaultValue;
						}
					}

					/** fills the output flow with the input flow */
					public void fill(outSMARTStruct input_row,
							row1Struct output_row) throws Exception {
						output_row.EXCODIGOEMPRESA = (String) coalesce(
								input_row.EXCODIGOEMPRESA, ' ', null, "");
						output_row.EXMODELODOCUMENTO = (String) coalesce(
								input_row.EXMODELODOCUMENTO, ' ', null, "");
						output_row.EXSERIEDOCUMENTO = (String) coalesce(
								input_row.EXSERIEDOCUMENTO, ' ', null, "");
						output_row.EXNUMERODOCUMENTO = (String) coalesce(
								input_row.EXNUMERODOCUMENTO, ' ', null, "");
						output_row.EXDATAEMISSAO = (java.util.Date) coalesce(
								input_row.EXDATAEMISSAO, null, null,
								TalendDate
										.parseDate("yyyy-MM-dd", "1970-01-01"));
						output_row.EXCFOP = (String) coalesce(input_row.EXCFOP,
								' ', null, "");
						output_row.EXCODIGOFORNECEDOR = (String) coalesce(
								input_row.EXCODIGOFORNECEDOR, ' ', null, "");
						output_row.EXTIPODECL = (String) coalesce(
								input_row.EXTIPODECL, ' ', null, "");
						output_row.EXNUMERODECLARACAO = (String) coalesce(
								input_row.EXNUMERODECLARACAO, ' ', null, "");
						output_row.EXDATADECLARACAO = input_row.EXDATADECLARACAO;
						output_row.EXDATAENTRADA = input_row.EXDATAENTRADA;
						output_row.EXVALORTOTAL = (String) coalesce(
								input_row.EXVALORTOTAL, ' ', null, "");
						output_row.EXDATAEMBARQUE = input_row.EXDATAEMBARQUE;
						output_row.EXNUMEROCONHECIMENTO = (String) coalesce(
								input_row.EXNUMEROCONHECIMENTO, ' ', null, "");
						output_row.EXDATACONHECIMENTO = input_row.EXDATACONHECIMENTO;
						output_row.EXTIPOCONHECIMENTO = (String) coalesce(
								input_row.EXTIPOCONHECIMENTO, ' ', null, "");
						output_row.EXNOMEPAIS = (String) coalesce(
								input_row.EXNOMEPAIS, ' ', null, "");
						output_row.EXCODIGOPAIS = (String) coalesce(
								input_row.EXCODIGOPAIS, ' ', null, "");
						output_row.EXDATASISCOMEX = input_row.EXDATASISCOMEX;
						output_row.EXDATADESEMBARACO = input_row.EXDATADESEMBARACO;
						output_row.EXVALORMERCADORIAS = (String) coalesce(
								input_row.EXVALORMERCADORIAS, ' ', null, "");
						output_row.EXVALORPIS = (String) coalesce(
								input_row.EXVALORPIS, ' ', null, "");
						output_row.EXVALORCOFINS = (String) coalesce(
								input_row.EXVALORCOFINS, ' ', null, "");
						output_row.EXVALORADUANA = (String) coalesce(
								input_row.EXVALORADUANA, ' ', null, "");
						output_row.EXVALORADUANAICMS = (String) coalesce(
								input_row.EXVALORADUANAICMS, ' ', null, "");
						output_row.EXVALORIOF = (String) coalesce(
								input_row.EXVALORIOF, ' ', null, "");
						output_row.EXVALORIMPOSTOIMPORT = (String) coalesce(
								input_row.EXVALORIMPOSTOIMPORT, ' ', null, "");
						output_row.EXVALORBASEIPI = (String) coalesce(
								input_row.EXVALORBASEIPI, ' ', null, "");
						output_row.EXVALORIPI = (String) coalesce(
								input_row.EXVALORIPI, ' ', null, "");
						output_row.EXVALORBASEICM = (String) coalesce(
								input_row.EXVALORBASEICM, ' ', null, "");
						output_row.EXVALORICM = (String) coalesce(
								input_row.EXVALORICM, ' ', null, "");
						output_row.EXLOCDESEMB = (String) coalesce(
								input_row.EXLOCDESEMB, ' ', null, "");
						output_row.EXUFDESEMB = (String) coalesce(
								input_row.EXUFDESEMB, ' ', null, "");
					} // end of fill

				} // end of class
				final Helper_tReplaceNull_1 helper_tReplaceNull_1 = new Helper_tReplaceNull_1();
				int nb_line_tReplaceNull_1 = 0;

				/**
				 * [tReplaceNull_1 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				int tos_count_tMap_1 = 0;

				// ###############################
				// # Lookup's keys initialization
				// ###############################

				// ###############################
				// # Vars initialization
				class Var__tMap_1__Struct {
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
				// ###############################

				// ###############################
				// # Outputs initialization
				outSMARTStruct outSMART_tmp = new outSMARTStruct();
				// ###############################

				/**
				 * [tMap_1 begin ] stop
				 */

				/**
				 * [tSAPInput_1 begin ] start
				 */

				ok_Hash.put("tSAPInput_1", false);
				start_Hash.put("tSAPInput_1", System.currentTimeMillis());

				currentComponent = "tSAPInput_1";

				int tos_count_tSAPInput_1 = 0;

				com.sap.conn.jco.JCoDestination dest_tSAPInput_1 = null;

				dest_tSAPInput_1 = (com.sap.conn.jco.JCoDestination) globalMap
						.get("conn_tSAPConnection_1");

				com.sap.conn.jco.JCoRepository repository_tSAPInput_1 = dest_tSAPInput_1
						.getRepository();

				com.sap.conn.jco.JCoFunctionTemplate functionTemplate_tSAPInput_1 = repository_tSAPInput_1
						.getFunctionTemplate("ZMSAF_CEX_IMPORT");
				if (functionTemplate_tSAPInput_1 == null) {
					com.sap.conn.jco.JCoContext.end(dest_tSAPInput_1);
					throw new RuntimeException(
							"The RFC can't support the function: "
									+ "ZMSAF_CEX_IMPORT" + ".");
				}
				com.sap.conn.jco.JCoFunction function_tSAPInput_1 = functionTemplate_tSAPInput_1
						.getFunction();

				com.sap.conn.jco.JCoParameterList importParameterList_tSAPInput_1 = function_tSAPInput_1
						.getImportParameterList();
				com.sap.conn.jco.JCoParameterList exportParameterList_tSAPInput_1 = function_tSAPInput_1
						.getExportParameterList();
				com.sap.conn.jco.JCoParameterList tableParameterList_tSAPInput_1 = function_tSAPInput_1
						.getTableParameterList();

				com.sap.conn.jco.JCoStructure input_structure_tSAPInput_1 = null;
				com.sap.conn.jco.JCoTable input_table_tSAPInput_1 = null;
				com.sap.conn.jco.JCoTable table_input_tSAPInput_1 = null;

				Object isListObject_tSAPInput_1 = null;

				importParameterList_tSAPInput_1.setValue("I_DATAATE",
						context.dataate); // "input_single"

				importParameterList_tSAPInput_1.setValue("I_DATADE",
						context.datade); // "input_single"

				importParameterList_tSAPInput_1.setValue("I_EMPRESA",
						context.empresa); // "input_single"

				try {
					function_tSAPInput_1.execute(dest_tSAPInput_1);
				} catch (java.lang.Exception e_tSAPInput_1) {
					com.sap.conn.jco.JCoContext.end(dest_tSAPInput_1);
					throw new RuntimeException(e_tSAPInput_1.getMessage());
				}

				boolean go_CEXIMPORT = true;

				com.sap.conn.jco.JCoTable table_CEXIMPORT_tSAPInput_1 = tableParameterList_tSAPInput_1
						.getTable("T_DATOS");

				go_CEXIMPORT = !table_CEXIMPORT_tSAPInput_1.isEmpty();

				while (

				go_CEXIMPORT) {

					CEXIMPORT = null;

					if (go_CEXIMPORT) {

						if (table_CEXIMPORT_tSAPInput_1.isLastRow()) { // check
																		// the
																		// flag
																		// first
							go_CEXIMPORT = false;
						}
						CEXIMPORT = new CEXIMPORTStruct();

						// "table_output" or "output_table"--EXCODIGOEMPRESA

						CEXIMPORT.EXCODIGOEMPRESA = ParserUtils
								.parseTo_String(table_CEXIMPORT_tSAPInput_1
										.getString("EXCODIGOEMPRESA"));

						// "table_output" or "output_table"--EXMODELODOCUMENTO

						CEXIMPORT.EXMODELODOCUMENTO = ParserUtils
								.parseTo_String(table_CEXIMPORT_tSAPInput_1
										.getString("EXMODELODOCUMENTO"));

						// "table_output" or "output_table"--EXSERIEDOCUMENTO

						CEXIMPORT.EXSERIEDOCUMENTO = ParserUtils
								.parseTo_String(table_CEXIMPORT_tSAPInput_1
										.getString("EXSERIEDOCUMENTO"));

						// "table_output" or "output_table"--EXNUMERODOCUMENTO

						CEXIMPORT.EXNUMERODOCUMENTO = ParserUtils
								.parseTo_String(table_CEXIMPORT_tSAPInput_1
										.getString("EXNUMERODOCUMENTO"));

						// "table_output" or "output_table"--EXDATAEMISSAO

						//
						CEXIMPORT.EXDATAEMISSAO = ParserUtils
								.parseTo_Date(table_CEXIMPORT_tSAPInput_1
										.getString("EXDATAEMISSAO"), "yy-MM-dd");

						// "table_output" or "output_table"--EXCFOP

						CEXIMPORT.EXCFOP = ParserUtils
								.parseTo_String(table_CEXIMPORT_tSAPInput_1
										.getString("EXCFOP"));

						// "table_output" or "output_table"--EXCODIGOFORNECEDOR

						CEXIMPORT.EXCODIGOFORNECEDOR = ParserUtils
								.parseTo_String(table_CEXIMPORT_tSAPInput_1
										.getString("EXCODIGOFORNECEDOR"));

						// "table_output" or "output_table"--EXTIPODECL

						CEXIMPORT.EXTIPODECL = ParserUtils
								.parseTo_String(table_CEXIMPORT_tSAPInput_1
										.getString("EXTIPODECL"));

						// "table_output" or "output_table"--EXNUMERODECLARACAO

						CEXIMPORT.EXNUMERODECLARACAO = ParserUtils
								.parseTo_String(table_CEXIMPORT_tSAPInput_1
										.getString("EXNUMERODECLARACAO"));

						// "table_output" or "output_table"--EXDATADECLARACAO

						//
						CEXIMPORT.EXDATADECLARACAO = ParserUtils.parseTo_Date(
								table_CEXIMPORT_tSAPInput_1
										.getString("EXDATADECLARACAO"),
								"yy-MM-dd");

						// "table_output" or "output_table"--EXDATAENTRADA

						//
						CEXIMPORT.EXDATAENTRADA = ParserUtils
								.parseTo_Date(table_CEXIMPORT_tSAPInput_1
										.getString("EXDATAENTRADA"), "yy-MM-dd");

						// "table_output" or "output_table"--EXVALORTOTAL

						CEXIMPORT.EXVALORTOTAL = ParserUtils
								.parseTo_String(table_CEXIMPORT_tSAPInput_1
										.getString("EXVALORTOTAL"));

						// "table_output" or "output_table"--EXDATAEMBARQUE

						//
						CEXIMPORT.EXDATAEMBARQUE = ParserUtils.parseTo_Date(
								table_CEXIMPORT_tSAPInput_1
										.getString("EXDATAEMBARQUE"),
								"yy-MM-dd");

						// "table_output" or
						// "output_table"--EXNUMEROCONHECIMENTO

						CEXIMPORT.EXNUMEROCONHECIMENTO = ParserUtils
								.parseTo_String(table_CEXIMPORT_tSAPInput_1
										.getString("EXNUMEROCONHECIMENTO"));

						// "table_output" or "output_table"--EXDATACONHECIMENTO

						CEXIMPORT.EXDATACONHECIMENTO = ParserUtils
								.parseTo_String(table_CEXIMPORT_tSAPInput_1
										.getString("EXDATACONHECIMENTO"));

						// "table_output" or "output_table"--EXTIPOCONHECIMENTO

						CEXIMPORT.EXTIPOCONHECIMENTO = ParserUtils
								.parseTo_String(table_CEXIMPORT_tSAPInput_1
										.getString("EXTIPOCONHECIMENTO"));

						// "table_output" or "output_table"--EXNOMEPAIS

						CEXIMPORT.EXNOMEPAIS = ParserUtils
								.parseTo_String(table_CEXIMPORT_tSAPInput_1
										.getString("EXNOMEPAIS"));

						// "table_output" or "output_table"--EXCODIGOPAIS

						CEXIMPORT.EXCODIGOPAIS = ParserUtils
								.parseTo_String(table_CEXIMPORT_tSAPInput_1
										.getString("EXCODIGOPAIS"));

						// "table_output" or "output_table"--EXDATASISCOMEX

						//
						CEXIMPORT.EXDATASISCOMEX = ParserUtils.parseTo_Date(
								table_CEXIMPORT_tSAPInput_1
										.getString("EXDATASISCOMEX"),
								"yy-MM-dd");

						// "table_output" or "output_table"--EXDATADESEMBARACO

						//
						CEXIMPORT.EXDATADESEMBARACO = ParserUtils.parseTo_Date(
								table_CEXIMPORT_tSAPInput_1
										.getString("EXDATADESEMBARACO"),
								"yy-MM-dd");

						// "table_output" or "output_table"--EXVALORMERCADORIAS

						CEXIMPORT.EXVALORMERCADORIAS = ParserUtils
								.parseTo_String(table_CEXIMPORT_tSAPInput_1
										.getString("EXVALORMERCADORIAS"));

						// "table_output" or "output_table"--EXVALORPIS

						CEXIMPORT.EXVALORPIS = ParserUtils
								.parseTo_String(table_CEXIMPORT_tSAPInput_1
										.getString("EXVALORPIS"));

						// "table_output" or "output_table"--EXVALORCOFINS

						CEXIMPORT.EXVALORCOFINS = ParserUtils
								.parseTo_String(table_CEXIMPORT_tSAPInput_1
										.getString("EXVALORCOFINS"));

						// "table_output" or "output_table"--EXVALORADUANA

						CEXIMPORT.EXVALORADUANA = ParserUtils
								.parseTo_String(table_CEXIMPORT_tSAPInput_1
										.getString("EXVALORADUANA"));

						// "table_output" or "output_table"--EXVALORADUANAICMS

						CEXIMPORT.EXVALORADUANAICMS = ParserUtils
								.parseTo_String(table_CEXIMPORT_tSAPInput_1
										.getString("EXVALORADUANAICMS"));

						// "table_output" or "output_table"--EXVALORIOF

						CEXIMPORT.EXVALORIOF = ParserUtils
								.parseTo_String(table_CEXIMPORT_tSAPInput_1
										.getString("EXVALORIOF"));

						// "table_output" or
						// "output_table"--EXVALORIMPOSTOIMPORT

						CEXIMPORT.EXVALORIMPOSTOIMPORT = ParserUtils
								.parseTo_String(table_CEXIMPORT_tSAPInput_1
										.getString("EXVALORIMPOSTOIMPORT"));

						// "table_output" or "output_table"--EXVALORBASEIPI

						CEXIMPORT.EXVALORBASEIPI = ParserUtils
								.parseTo_String(table_CEXIMPORT_tSAPInput_1
										.getString("EXVALORBASEIPI"));

						// "table_output" or "output_table"--EXVALORIPI

						CEXIMPORT.EXVALORIPI = ParserUtils
								.parseTo_String(table_CEXIMPORT_tSAPInput_1
										.getString("EXVALORIPI"));

						// "table_output" or "output_table"--EXVALORBASEICM

						CEXIMPORT.EXVALORBASEICM = ParserUtils
								.parseTo_String(table_CEXIMPORT_tSAPInput_1
										.getString("EXVALORBASEICM"));

						// "table_output" or "output_table"--EXVALORICM

						CEXIMPORT.EXVALORICM = ParserUtils
								.parseTo_String(table_CEXIMPORT_tSAPInput_1
										.getString("EXVALORICM"));

						// "table_output" or "output_table"--EXLOCDESEMB

						CEXIMPORT.EXLOCDESEMB = ParserUtils
								.parseTo_String(table_CEXIMPORT_tSAPInput_1
										.getString("EXLOCDESEMB"));

						// "table_output" or "output_table"--EXUFDESEMB

						CEXIMPORT.EXUFDESEMB = ParserUtils
								.parseTo_String(table_CEXIMPORT_tSAPInput_1
										.getString("EXUFDESEMB"));

						if (go_CEXIMPORT) {
							table_CEXIMPORT_tSAPInput_1.nextRow();
						}

					}

					/**
					 * [tSAPInput_1 begin ] stop
					 */

					/**
					 * [tSAPInput_1 main ] start
					 */

					currentComponent = "tSAPInput_1";

					tos_count_tSAPInput_1++;

					/**
					 * [tSAPInput_1 main ] stop
					 */
					// Start of branch "CEXIMPORT"
					if (CEXIMPORT != null) {

						/**
						 * [tMap_1 main ] start
						 */

						currentComponent = "tMap_1";

						boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

						// ###############################
						// # Input tables (lookups)
						boolean rejectedInnerJoin_tMap_1 = false;
						boolean mainRowRejected_tMap_1 = false;

						// ###############################
						{ // start of Var scope

							// ###############################
							// # Vars tables

							Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
							// ###############################
							// # Output tables

							outSMART = null;

							// # Output table : 'outSMART'
							outSMART_tmp.EXCODIGOEMPRESA = "001";
							outSMART_tmp.EXMODELODOCUMENTO = CEXIMPORT.EXMODELODOCUMENTO;
							outSMART_tmp.EXSERIEDOCUMENTO = CEXIMPORT.EXSERIEDOCUMENTO;
							outSMART_tmp.EXNUMERODOCUMENTO = CEXIMPORT.EXNUMERODOCUMENTO;
							outSMART_tmp.EXDATAEMISSAO = CEXIMPORT.EXDATAEMISSAO;
							outSMART_tmp.EXCFOP = CEXIMPORT.EXCFOP;
							outSMART_tmp.EXCODIGOFORNECEDOR = CEXIMPORT.EXCODIGOFORNECEDOR;
							outSMART_tmp.EXTIPODECL = CEXIMPORT.EXTIPODECL;
							outSMART_tmp.EXNUMERODECLARACAO = CEXIMPORT.EXNUMERODECLARACAO;
							outSMART_tmp.EXDATADECLARACAO = CEXIMPORT.EXDATADECLARACAO;
							outSMART_tmp.EXDATAENTRADA = CEXIMPORT.EXDATAENTRADA;
							outSMART_tmp.EXVALORTOTAL = CEXIMPORT.EXVALORTOTAL;
							outSMART_tmp.EXDATAEMBARQUE = CEXIMPORT.EXDATAEMBARQUE;
							outSMART_tmp.EXNUMEROCONHECIMENTO = CEXIMPORT.EXNUMEROCONHECIMENTO;
							outSMART_tmp.EXDATACONHECIMENTO = CEXIMPORT.EXDATACONHECIMENTO;
							outSMART_tmp.EXTIPOCONHECIMENTO = CEXIMPORT.EXTIPOCONHECIMENTO;
							outSMART_tmp.EXNOMEPAIS = CEXIMPORT.EXNOMEPAIS;
							outSMART_tmp.EXCODIGOPAIS = CEXIMPORT.EXCODIGOPAIS;
							outSMART_tmp.EXDATASISCOMEX = CEXIMPORT.EXDATASISCOMEX;
							outSMART_tmp.EXDATADESEMBARACO = CEXIMPORT.EXDATADESEMBARACO;
							outSMART_tmp.EXVALORMERCADORIAS = CEXIMPORT.EXVALORMERCADORIAS;
							outSMART_tmp.EXVALORPIS = CEXIMPORT.EXVALORPIS;
							outSMART_tmp.EXVALORCOFINS = CEXIMPORT.EXVALORCOFINS;
							outSMART_tmp.EXVALORADUANA = CEXIMPORT.EXVALORADUANA;
							outSMART_tmp.EXVALORADUANAICMS = CEXIMPORT.EXVALORADUANAICMS;
							outSMART_tmp.EXVALORIOF = CEXIMPORT.EXVALORIOF;
							outSMART_tmp.EXVALORIMPOSTOIMPORT = CEXIMPORT.EXVALORIMPOSTOIMPORT;
							outSMART_tmp.EXVALORBASEIPI = CEXIMPORT.EXVALORBASEIPI;
							outSMART_tmp.EXVALORIPI = CEXIMPORT.EXVALORIPI;
							outSMART_tmp.EXVALORBASEICM = CEXIMPORT.EXVALORBASEICM;
							outSMART_tmp.EXVALORICM = CEXIMPORT.EXVALORICM;
							outSMART_tmp.EXLOCDESEMB = CEXIMPORT.EXLOCDESEMB;
							outSMART_tmp.EXUFDESEMB = CEXIMPORT.EXUFDESEMB;
							outSMART = outSMART_tmp;
							// ###############################

						} // end of Var scope

						rejectedInnerJoin_tMap_1 = false;

						tos_count_tMap_1++;

						/**
						 * [tMap_1 main ] stop
						 */
						// Start of branch "outSMART"
						if (outSMART != null) {

							/**
							 * [tReplaceNull_1 main ] start
							 */

							currentComponent = "tReplaceNull_1";

							nb_line_tReplaceNull_1++;
							helper_tReplaceNull_1.fill(outSMART, row1);

							tos_count_tReplaceNull_1++;

							/**
							 * [tReplaceNull_1 main ] stop
							 */

							/**
							 * [tReplace_1 main ] start
							 */

							currentComponent = "tReplace_1";

							row2.EXCODIGOEMPRESA = row1.EXCODIGOEMPRESA;

							row2.EXMODELODOCUMENTO = row1.EXMODELODOCUMENTO;

							row2.EXSERIEDOCUMENTO = row1.EXSERIEDOCUMENTO;

							row2.EXNUMERODOCUMENTO = row1.EXNUMERODOCUMENTO;

							row2.EXDATAEMISSAO = row1.EXDATAEMISSAO;

							row2.EXCFOP = row1.EXCFOP;

							row2.EXCODIGOFORNECEDOR = row1.EXCODIGOFORNECEDOR;

							row2.EXTIPODECL = row1.EXTIPODECL;

							row2.EXNUMERODECLARACAO = row1.EXNUMERODECLARACAO;

							row2.EXDATADECLARACAO = row1.EXDATADECLARACAO;

							row2.EXDATAENTRADA = row1.EXDATAENTRADA;

							row2.EXVALORTOTAL = row1.EXVALORTOTAL;

							row2.EXDATAEMBARQUE = row1.EXDATAEMBARQUE;

							row2.EXNUMEROCONHECIMENTO = row1.EXNUMEROCONHECIMENTO;

							row2.EXDATACONHECIMENTO = row1.EXDATACONHECIMENTO;

							row2.EXTIPOCONHECIMENTO = row1.EXTIPOCONHECIMENTO;

							row2.EXNOMEPAIS = row1.EXNOMEPAIS;

							row2.EXCODIGOPAIS = row1.EXCODIGOPAIS;

							row2.EXDATASISCOMEX = row1.EXDATASISCOMEX;

							row2.EXDATADESEMBARACO = row1.EXDATADESEMBARACO;

							row2.EXVALORMERCADORIAS = row1.EXVALORMERCADORIAS;

							row2.EXVALORPIS = row1.EXVALORPIS;

							row2.EXVALORCOFINS = row1.EXVALORCOFINS;

							row2.EXVALORADUANA = row1.EXVALORADUANA;

							row2.EXVALORADUANAICMS = row1.EXVALORADUANAICMS;

							row2.EXVALORIOF = row1.EXVALORIOF;

							row2.EXVALORIMPOSTOIMPORT = row1.EXVALORIMPOSTOIMPORT;

							row2.EXVALORBASEIPI = row1.EXVALORBASEIPI;

							row2.EXVALORIPI = row1.EXVALORIPI;

							row2.EXVALORBASEICM = row1.EXVALORBASEICM;

							row2.EXVALORICM = row1.EXVALORICM;

							row2.EXLOCDESEMB = row1.EXLOCDESEMB;

							row2.EXUFDESEMB = row1.EXUFDESEMB;

							nb_line_tReplace_1++;

							tos_count_tReplace_1++;

							/**
							 * [tReplace_1 main ] stop
							 */

							/**
							 * [tMSSqlOutput_1 main ] start
							 */

							currentComponent = "tMSSqlOutput_1";

							whetherReject_tMSSqlOutput_1 = false;
							if (row2.EXCODIGOEMPRESA == null) {
								pstmt_tMSSqlOutput_1.setNull(1,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(1,
										row2.EXCODIGOEMPRESA);
							}

							if (row2.EXMODELODOCUMENTO == null) {
								pstmt_tMSSqlOutput_1.setNull(2,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(2,
										row2.EXMODELODOCUMENTO);
							}

							if (row2.EXSERIEDOCUMENTO == null) {
								pstmt_tMSSqlOutput_1.setNull(3,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(3,
										row2.EXSERIEDOCUMENTO);
							}

							if (row2.EXNUMERODOCUMENTO == null) {
								pstmt_tMSSqlOutput_1.setNull(4,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(4,
										row2.EXNUMERODOCUMENTO);
							}

							if (row2.EXDATAEMISSAO != null) {
								pstmt_tMSSqlOutput_1.setTimestamp(5,
										new java.sql.Timestamp(
												row2.EXDATAEMISSAO.getTime()));
							} else {
								pstmt_tMSSqlOutput_1.setNull(5,
										java.sql.Types.DATE);
							}

							if (row2.EXCFOP == null) {
								pstmt_tMSSqlOutput_1.setNull(6,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(6, row2.EXCFOP);
							}

							if (row2.EXCODIGOFORNECEDOR == null) {
								pstmt_tMSSqlOutput_1.setNull(7,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(7,
										row2.EXCODIGOFORNECEDOR);
							}

							if (row2.EXTIPODECL == null) {
								pstmt_tMSSqlOutput_1.setNull(8,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(8,
										row2.EXTIPODECL);
							}

							if (row2.EXNUMERODECLARACAO == null) {
								pstmt_tMSSqlOutput_1.setNull(9,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(9,
										row2.EXNUMERODECLARACAO);
							}

							if (row2.EXDATADECLARACAO != null) {
								pstmt_tMSSqlOutput_1
										.setTimestamp(
												10,
												new java.sql.Timestamp(
														row2.EXDATADECLARACAO
																.getTime()));
							} else {
								pstmt_tMSSqlOutput_1.setNull(10,
										java.sql.Types.DATE);
							}

							if (row2.EXDATAENTRADA != null) {
								pstmt_tMSSqlOutput_1.setTimestamp(11,
										new java.sql.Timestamp(
												row2.EXDATAENTRADA.getTime()));
							} else {
								pstmt_tMSSqlOutput_1.setNull(11,
										java.sql.Types.DATE);
							}

							if (row2.EXVALORTOTAL == null) {
								pstmt_tMSSqlOutput_1.setNull(12,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(12,
										row2.EXVALORTOTAL);
							}

							if (row2.EXDATAEMBARQUE != null) {
								pstmt_tMSSqlOutput_1.setTimestamp(13,
										new java.sql.Timestamp(
												row2.EXDATAEMBARQUE.getTime()));
							} else {
								pstmt_tMSSqlOutput_1.setNull(13,
										java.sql.Types.DATE);
							}

							if (row2.EXNUMEROCONHECIMENTO == null) {
								pstmt_tMSSqlOutput_1.setNull(14,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(14,
										row2.EXNUMEROCONHECIMENTO);
							}

							if (row2.EXDATACONHECIMENTO == null) {
								pstmt_tMSSqlOutput_1.setNull(15,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(15,
										row2.EXDATACONHECIMENTO);
							}

							if (row2.EXTIPOCONHECIMENTO == null) {
								pstmt_tMSSqlOutput_1.setNull(16,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(16,
										row2.EXTIPOCONHECIMENTO);
							}

							if (row2.EXNOMEPAIS == null) {
								pstmt_tMSSqlOutput_1.setNull(17,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(17,
										row2.EXNOMEPAIS);
							}

							if (row2.EXCODIGOPAIS == null) {
								pstmt_tMSSqlOutput_1.setNull(18,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(18,
										row2.EXCODIGOPAIS);
							}

							if (row2.EXDATASISCOMEX != null) {
								pstmt_tMSSqlOutput_1.setTimestamp(19,
										new java.sql.Timestamp(
												row2.EXDATASISCOMEX.getTime()));
							} else {
								pstmt_tMSSqlOutput_1.setNull(19,
										java.sql.Types.DATE);
							}

							if (row2.EXDATADESEMBARACO != null) {
								pstmt_tMSSqlOutput_1.setTimestamp(
										20,
										new java.sql.Timestamp(
												row2.EXDATADESEMBARACO
														.getTime()));
							} else {
								pstmt_tMSSqlOutput_1.setNull(20,
										java.sql.Types.DATE);
							}

							if (row2.EXVALORMERCADORIAS == null) {
								pstmt_tMSSqlOutput_1.setNull(21,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(21,
										row2.EXVALORMERCADORIAS);
							}

							if (row2.EXVALORPIS == null) {
								pstmt_tMSSqlOutput_1.setNull(22,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(22,
										row2.EXVALORPIS);
							}

							if (row2.EXVALORCOFINS == null) {
								pstmt_tMSSqlOutput_1.setNull(23,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(23,
										row2.EXVALORCOFINS);
							}

							if (row2.EXVALORADUANA == null) {
								pstmt_tMSSqlOutput_1.setNull(24,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(24,
										row2.EXVALORADUANA);
							}

							if (row2.EXVALORADUANAICMS == null) {
								pstmt_tMSSqlOutput_1.setNull(25,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(25,
										row2.EXVALORADUANAICMS);
							}

							if (row2.EXVALORIOF == null) {
								pstmt_tMSSqlOutput_1.setNull(26,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(26,
										row2.EXVALORIOF);
							}

							if (row2.EXVALORIMPOSTOIMPORT == null) {
								pstmt_tMSSqlOutput_1.setNull(27,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(27,
										row2.EXVALORIMPOSTOIMPORT);
							}

							if (row2.EXVALORBASEIPI == null) {
								pstmt_tMSSqlOutput_1.setNull(28,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(28,
										row2.EXVALORBASEIPI);
							}

							if (row2.EXVALORIPI == null) {
								pstmt_tMSSqlOutput_1.setNull(29,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(29,
										row2.EXVALORIPI);
							}

							if (row2.EXVALORBASEICM == null) {
								pstmt_tMSSqlOutput_1.setNull(30,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(30,
										row2.EXVALORBASEICM);
							}

							if (row2.EXVALORICM == null) {
								pstmt_tMSSqlOutput_1.setNull(31,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(31,
										row2.EXVALORICM);
							}

							if (row2.EXLOCDESEMB == null) {
								pstmt_tMSSqlOutput_1.setNull(32,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(32,
										row2.EXLOCDESEMB);
							}

							if (row2.EXUFDESEMB == null) {
								pstmt_tMSSqlOutput_1.setNull(33,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(33,
										row2.EXUFDESEMB);
							}

							pstmt_tMSSqlOutput_1.addBatch();
							nb_line_tMSSqlOutput_1++;

							batchSizeCounter_tMSSqlOutput_1++;

							if ((batchSize_tMSSqlOutput_1 > 0)
									&& (batchSize_tMSSqlOutput_1 <= batchSizeCounter_tMSSqlOutput_1)) {
								try {
									int countSum_tMSSqlOutput_1 = 0;

									for (int countEach_tMSSqlOutput_1 : pstmt_tMSSqlOutput_1
											.executeBatch()) {
										if (countEach_tMSSqlOutput_1 == -2
												|| countEach_tMSSqlOutput_1 == -3) {
											break;
										}
										countSum_tMSSqlOutput_1 += countEach_tMSSqlOutput_1;
									}

									insertedCount_tMSSqlOutput_1 += countSum_tMSSqlOutput_1;

									batchSizeCounter_tMSSqlOutput_1 = 0;
								} catch (java.sql.BatchUpdateException e) {

									int countSum_tMSSqlOutput_1 = 0;
									for (int countEach_tMSSqlOutput_1 : e
											.getUpdateCounts()) {
										countSum_tMSSqlOutput_1 += (countEach_tMSSqlOutput_1 < 0 ? 0
												: countEach_tMSSqlOutput_1);
									}

									insertedCount_tMSSqlOutput_1 += countSum_tMSSqlOutput_1;

									System.err.println(e.getMessage());

								}
							}

							commitCounter_tMSSqlOutput_1++;
							if (commitEvery_tMSSqlOutput_1 <= commitCounter_tMSSqlOutput_1) {
								if ((batchSize_tMSSqlOutput_1 > 0)
										&& (batchSizeCounter_tMSSqlOutput_1 > 0)) {
									try {
										int countSum_tMSSqlOutput_1 = 0;

										for (int countEach_tMSSqlOutput_1 : pstmt_tMSSqlOutput_1
												.executeBatch()) {
											if (countEach_tMSSqlOutput_1 == -2
													|| countEach_tMSSqlOutput_1 == -3) {
												break;
											}
											countSum_tMSSqlOutput_1 += countEach_tMSSqlOutput_1;
										}

										insertedCount_tMSSqlOutput_1 += countSum_tMSSqlOutput_1;

										batchSizeCounter_tMSSqlOutput_1 = 0;
									} catch (java.sql.BatchUpdateException e) {

										int countSum_tMSSqlOutput_1 = 0;
										for (int countEach_tMSSqlOutput_1 : e
												.getUpdateCounts()) {
											countSum_tMSSqlOutput_1 += (countEach_tMSSqlOutput_1 < 0 ? 0
													: countEach_tMSSqlOutput_1);
										}

										insertedCount_tMSSqlOutput_1 += countSum_tMSSqlOutput_1;

										System.err.println(e.getMessage());

									}
								}

								conn_tMSSqlOutput_1.commit();

								commitCounter_tMSSqlOutput_1 = 0;
							}

							tos_count_tMSSqlOutput_1++;

							/**
							 * [tMSSqlOutput_1 main ] stop
							 */

						} // End of branch "outSMART"

					} // End of branch "CEXIMPORT"

					/**
					 * [tSAPInput_1 end ] start
					 */

					currentComponent = "tSAPInput_1";

				}

				com.sap.conn.jco.JCoContext.end(dest_tSAPInput_1);

				ok_Hash.put("tSAPInput_1", true);
				end_Hash.put("tSAPInput_1", System.currentTimeMillis());

				/**
				 * [tSAPInput_1 end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

				// ###############################
				// # Lookup hashes releasing
				// ###############################

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
				 */

				/**
				 * [tReplaceNull_1 end ] start
				 */

				currentComponent = "tReplaceNull_1";

				globalMap.put("tReplaceNull_1_NB_LINE", nb_line_tReplaceNull_1);

				ok_Hash.put("tReplaceNull_1", true);
				end_Hash.put("tReplaceNull_1", System.currentTimeMillis());

				/**
				 * [tReplaceNull_1 end ] stop
				 */

				/**
				 * [tReplace_1 end ] start
				 */

				currentComponent = "tReplace_1";

				globalMap.put("tReplace_1_NB_LINE", nb_line_tReplace_1);

				ok_Hash.put("tReplace_1", true);
				end_Hash.put("tReplace_1", System.currentTimeMillis());

				/**
				 * [tReplace_1 end ] stop
				 */

				/**
				 * [tMSSqlOutput_1 end ] start
				 */

				currentComponent = "tMSSqlOutput_1";

				try {
					int countSum_tMSSqlOutput_1 = 0;
					if (pstmt_tMSSqlOutput_1 != null
							&& batchSizeCounter_tMSSqlOutput_1 > 0) {

						for (int countEach_tMSSqlOutput_1 : pstmt_tMSSqlOutput_1
								.executeBatch()) {
							if (countEach_tMSSqlOutput_1 == -2
									|| countEach_tMSSqlOutput_1 == -3) {
								break;
							}
							countSum_tMSSqlOutput_1 += countEach_tMSSqlOutput_1;
						}

					}

					insertedCount_tMSSqlOutput_1 += countSum_tMSSqlOutput_1;

				} catch (java.sql.BatchUpdateException e) {

					int countSum_tMSSqlOutput_1 = 0;
					for (int countEach_tMSSqlOutput_1 : e.getUpdateCounts()) {
						countSum_tMSSqlOutput_1 += (countEach_tMSSqlOutput_1 < 0 ? 0
								: countEach_tMSSqlOutput_1);
					}

					insertedCount_tMSSqlOutput_1 += countSum_tMSSqlOutput_1;

					System.err.println(e.getMessage());

				}
				if (pstmt_tMSSqlOutput_1 != null) {

					pstmt_tMSSqlOutput_1.close();

				}

				conn_tMSSqlOutput_1.commit();

				conn_tMSSqlOutput_1.close();
				resourceMap.put("finish_tMSSqlOutput_1", true);

				nb_line_deleted_tMSSqlOutput_1 = nb_line_deleted_tMSSqlOutput_1
						+ deletedCount_tMSSqlOutput_1;
				nb_line_update_tMSSqlOutput_1 = nb_line_update_tMSSqlOutput_1
						+ updatedCount_tMSSqlOutput_1;
				nb_line_inserted_tMSSqlOutput_1 = nb_line_inserted_tMSSqlOutput_1
						+ insertedCount_tMSSqlOutput_1;
				nb_line_rejected_tMSSqlOutput_1 = nb_line_rejected_tMSSqlOutput_1
						+ rejectedCount_tMSSqlOutput_1;

				globalMap.put("tMSSqlOutput_1_NB_LINE", nb_line_tMSSqlOutput_1);
				globalMap.put("tMSSqlOutput_1_NB_LINE_UPDATED",
						nb_line_update_tMSSqlOutput_1);
				globalMap.put("tMSSqlOutput_1_NB_LINE_INSERTED",
						nb_line_inserted_tMSSqlOutput_1);
				globalMap.put("tMSSqlOutput_1_NB_LINE_DELETED",
						nb_line_deleted_tMSSqlOutput_1);
				globalMap.put("tMSSqlOutput_1_NB_LINE_REJECTED",
						nb_line_rejected_tMSSqlOutput_1);

				ok_Hash.put("tMSSqlOutput_1", true);
				end_Hash.put("tMSSqlOutput_1", System.currentTimeMillis());

				/**
				 * [tMSSqlOutput_1 end ] stop
				 */

			}// end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent,
					globalMap);

			throw te;
		} catch (java.lang.Error error) {

			throw error;
		} finally {

			try {

				/**
				 * [tSAPInput_1 finally ] start
				 */

				currentComponent = "tSAPInput_1";

				/**
				 * [tSAPInput_1 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tReplaceNull_1 finally ] start
				 */

				currentComponent = "tReplaceNull_1";

				/**
				 * [tReplaceNull_1 finally ] stop
				 */

				/**
				 * [tReplace_1 finally ] start
				 */

				currentComponent = "tReplace_1";

				/**
				 * [tReplace_1 finally ] stop
				 */

				/**
				 * [tMSSqlOutput_1 finally ] start
				 */

				currentComponent = "tMSSqlOutput_1";

				if (resourceMap.get("finish_tMSSqlOutput_1") == null) {
					if (resourceMap.get("conn_tMSSqlOutput_1") != null) {
						try {

							((java.sql.Connection) resourceMap
									.get("conn_tMSSqlOutput_1")).close();

						} catch (java.sql.SQLException sqlEx_tMSSqlOutput_1) {
							String errorMessage_tMSSqlOutput_1 = "failed to close the connection in tMSSqlOutput_1 :"
									+ sqlEx_tMSSqlOutput_1.getMessage();

							System.err.println(errorMessage_tMSSqlOutput_1);
						}
					}
				}

				/**
				 * [tMSSqlOutput_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tSAPInput_1_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "PRD";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	private java.util.Properties context_param = new java.util.Properties();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final conn_ceximport conn_ceximportClass = new conn_ceximport();

		int exitCode = conn_ceximportClass.runJobInTOS(args);

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		try {
			// call job/subjob with an existing context, like:
			// --context=production. if without this parameter, there will use
			// the default context instead.
			java.io.InputStream inContext = conn_ceximport.class
					.getClassLoader().getResourceAsStream(
							"client/conn_ceximport_0_1/contexts/"
									+ contextStr + ".properties");
			if (isDefaultContext && inContext == null) {

			} else {
				if (inContext != null) {
					// defaultProps is in order to keep the original context
					// value
					defaultProps.load(inContext);
					inContext.close();
					context = new ContextProperties(defaultProps);
				} else {
					// print info and job continue to run, for case:
					// context_param is not empty.
					System.err.println("Could not find the context "
							+ contextStr);
				}
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
			}
			context.database = (String) context.getProperty("database");
			context.host = (String) context.getProperty("host");
			context.pass = (String) context.getProperty("pass");
			context.port = (String) context.getProperty("port");
			context.schema = (String) context.getProperty("schema");
			context.username = (String) context.getProperty("username");
			context.tabela = (String) context.getProperty("tabela");
			context.datade = (String) context.getProperty("datade");
			context.dataate = (String) context.getProperty("dataate");
			context.clientSap = (String) context.getProperty("clientSap");
			context.userID = (String) context.getProperty("userID");
			context.password = (String) context.getProperty("password");
			context.language = (String) context.getProperty("language");
			context.hostName = (String) context.getProperty("hostName");
			context.systemNumber = (String) context.getProperty("systemNumber");
			context.empresa = (String) context.getProperty("empresa");
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
			if (parentContextMap.containsKey("database")) {
				context.database = (String) parentContextMap.get("database");
			}
			if (parentContextMap.containsKey("host")) {
				context.host = (String) parentContextMap.get("host");
			}
			if (parentContextMap.containsKey("pass")) {
				context.pass = (String) parentContextMap.get("pass");
			}
			if (parentContextMap.containsKey("port")) {
				context.port = (String) parentContextMap.get("port");
			}
			if (parentContextMap.containsKey("schema")) {
				context.schema = (String) parentContextMap.get("schema");
			}
			if (parentContextMap.containsKey("username")) {
				context.username = (String) parentContextMap.get("username");
			}
			if (parentContextMap.containsKey("tabela")) {
				context.tabela = (String) parentContextMap.get("tabela");
			}
			if (parentContextMap.containsKey("datade")) {
				context.datade = (String) parentContextMap.get("datade");
			}
			if (parentContextMap.containsKey("dataate")) {
				context.dataate = (String) parentContextMap.get("dataate");
			}
			if (parentContextMap.containsKey("clientSap")) {
				context.clientSap = (String) parentContextMap.get("clientSap");
			}
			if (parentContextMap.containsKey("userID")) {
				context.userID = (String) parentContextMap.get("userID");
			}
			if (parentContextMap.containsKey("password")) {
				context.password = (String) parentContextMap.get("password");
			}
			if (parentContextMap.containsKey("language")) {
				context.language = (String) parentContextMap.get("language");
			}
			if (parentContextMap.containsKey("hostName")) {
				context.hostName = (String) parentContextMap.get("hostName");
			}
			if (parentContextMap.containsKey("systemNumber")) {
				context.systemNumber = (String) parentContextMap
						.get("systemNumber");
			}
			if (parentContextMap.containsKey("empresa")) {
				context.empresa = (String) parentContextMap.get("empresa");
			}
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil
				.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName,
				jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName,
				parent_part_launcher, Thread.currentThread().getId() + "", "",
				"", "", "",
				resumeUtil.convertToJsonText(context, parametersToEncrypt));

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory()
				- Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tSAPConnection_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tSAPConnection_1) {
			globalMap.put("tSAPConnection_1_SUBPROCESS_STATE", -1);

			e_tSAPConnection_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory()
				- Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory)
					+ " bytes memory increase when running : conn_ceximport");
		}

		int returnCode = 0;
		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher,
				Thread.currentThread().getId() + "", "", "" + returnCode, "",
				"", "");

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		connections.put("conn_tSAPConnection_1",
				globalMap.get("conn_tSAPConnection_1"));

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index),
							keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		}

	}

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" },
			{ "\\'", "\'" }, { "\\r", "\r" }, { "\\f", "\f" }, { "\\b", "\b" },
			{ "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex,
							index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left
			// into the result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 127105 characters generated by Talend Open Studio for Data Integration on the
 * 28 de Fevereiro de 2019 13h37min55s BRT
 ************************************************************************************************/
