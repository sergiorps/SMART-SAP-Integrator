package client.conn_nfsproduto_0_1;

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
 * Job: conn_nfsproduto Purpose: Conector SAP x SMART - Nota Fiscal de Saída Produtos<br>
 * Description: Conector SAP x SMART - Nota Fiscal de Saída Produtos <br>
 * @author sergio.paes@slaterit.com
 * @version 6.2.1.20160704_1411
 * @status 
 */
public class conn_nfsproduto implements TalendJob {

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
	private final String jobName = "conn_nfsproduto";
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
					conn_nfsproduto.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass()
							.getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(conn_nfsproduto.this, new Object[] { e,
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

	public void tLogRow_1_error(Exception exception, String errorComponent,
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

	public static class row3Struct implements
			routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_client_conn_nfsproduto = new byte[0];
		static byte[] commonByteArray_client_conn_nfsproduto = new byte[0];

		public String FACODIGOEMPRESA;

		public String getFACODIGOEMPRESA() {
			return this.FACODIGOEMPRESA;
		}

		public String FAEMITENTE;

		public String getFAEMITENTE() {
			return this.FAEMITENTE;
		}

		public String FAMODELODOCUMENTO;

		public String getFAMODELODOCUMENTO() {
			return this.FAMODELODOCUMENTO;
		}

		public String FATIPODOCUMENTO;

		public String getFATIPODOCUMENTO() {
			return this.FATIPODOCUMENTO;
		}

		public String FASERIEDOCUMENTO;

		public String getFASERIEDOCUMENTO() {
			return this.FASERIEDOCUMENTO;
		}

		public String FANUMERODOCUMENTO;

		public String getFANUMERODOCUMENTO() {
			return this.FANUMERODOCUMENTO;
		}

		public String FANFECHAVE;

		public String getFANFECHAVE() {
			return this.FANFECHAVE;
		}

		public java.util.Date FADATAEMISSAO;

		public java.util.Date getFADATAEMISSAO() {
			return this.FADATAEMISSAO;
		}

		public java.util.Date FADATASAIDA;

		public java.util.Date getFADATASAIDA() {
			return this.FADATASAIDA;
		}

		public String FAHORASAIDA;

		public String getFAHORASAIDA() {
			return this.FAHORASAIDA;
		}

		public String FACODIGOCLIENTE;

		public String getFACODIGOCLIENTE() {
			return this.FACODIGOCLIENTE;
		}

		public String FAUFCLIENTE;

		public String getFAUFCLIENTE() {
			return this.FAUFCLIENTE;
		}

		public String FASITUACAODOC;

		public String getFASITUACAODOC() {
			return this.FASITUACAODOC;
		}

		public String FAINDCANCELAMENTO;

		public String getFAINDCANCELAMENTO() {
			return this.FAINDCANCELAMENTO;
		}

		public String FACONSUMIDOR;

		public String getFACONSUMIDOR() {
			return this.FACONSUMIDOR;
		}

		public String FACONTRIBUINTE;

		public String getFACONTRIBUINTE() {
			return this.FACONTRIBUINTE;
		}

		public String FAVALORPRODUTOS;

		public String getFAVALORPRODUTOS() {
			return this.FAVALORPRODUTOS;
		}

		public String FATIPOFATURA;

		public String getFATIPOFATURA() {
			return this.FATIPOFATURA;
		}

		public String FATOTALNFISCAL;

		public String getFATOTALNFISCAL() {
			return this.FATOTALNFISCAL;
		}

		public String FABASEICMS;

		public String getFABASEICMS() {
			return this.FABASEICMS;
		}

		public String FATOTALICM;

		public String getFATOTALICM() {
			return this.FATOTALICM;
		}

		public String FABASEREDUCAOIPI;

		public String getFABASEREDUCAOIPI() {
			return this.FABASEREDUCAOIPI;
		}

		public String FAVALORTOTALIPI;

		public String getFAVALORTOTALIPI() {
			return this.FAVALORTOTALIPI;
		}

		public String FATOTALBASESUBST;

		public String getFATOTALBASESUBST() {
			return this.FATOTALBASESUBST;
		}

		public String FATOTALICMSUBST;

		public String getFATOTALICMSUBST() {
			return this.FATOTALICMSUBST;
		}

		public String FAINSCRICAOSUBSTITUTO;

		public String getFAINSCRICAOSUBSTITUTO() {
			return this.FAINSCRICAOSUBSTITUTO;
		}

		public String FAVALORFRETE;

		public String getFAVALORFRETE() {
			return this.FAVALORFRETE;
		}

		public String FAVALORSEGURO;

		public String getFAVALORSEGURO() {
			return this.FAVALORSEGURO;
		}

		public String FAVALOROUTRAS;

		public String getFAVALOROUTRAS() {
			return this.FAVALOROUTRAS;
		}

		public String FAVIATRANSPORTE;

		public String getFAVIATRANSPORTE() {
			return this.FAVIATRANSPORTE;
		}

		public String FAMODALIDADEFRETE;

		public String getFAMODALIDADEFRETE() {
			return this.FAMODALIDADEFRETE;
		}

		public String FACODIGOTRANSPORTADOR;

		public String getFACODIGOTRANSPORTADOR() {
			return this.FACODIGOTRANSPORTADOR;
		}

		public String FAQTDEVOLUMES;

		public String getFAQTDEVOLUMES() {
			return this.FAQTDEVOLUMES;
		}

		public String FAESPECIEVOLUMES;

		public String getFAESPECIEVOLUMES() {
			return this.FAESPECIEVOLUMES;
		}

		public String FAMARCAVOLUMES;

		public String getFAMARCAVOLUMES() {
			return this.FAMARCAVOLUMES;
		}

		public String FANUMERACAOVOLUMES;

		public String getFANUMERACAOVOLUMES() {
			return this.FANUMERACAOVOLUMES;
		}

		public String FAPESOBRUTO;

		public String getFAPESOBRUTO() {
			return this.FAPESOBRUTO;
		}

		public String FAPESOLIQUIDO;

		public String getFAPESOLIQUIDO() {
			return this.FAPESOLIQUIDO;
		}

		public String FAIDENTIFICACAOVEICULO;

		public String getFAIDENTIFICACAOVEICULO() {
			return this.FAIDENTIFICACAOVEICULO;
		}

		public String FATIPONOTA;

		public String getFATIPONOTA() {
			return this.FATIPONOTA;
		}

		public String ITNUMEROITEM;

		public String getITNUMEROITEM() {
			return this.ITNUMEROITEM;
		}

		public String ITCODIGOPRODUTO;

		public String getITCODIGOPRODUTO() {
			return this.ITCODIGOPRODUTO;
		}

		public String ITCFOP;

		public String getITCFOP() {
			return this.ITCFOP;
		}

		public String ITCLASSIFICACAOFISCAL;

		public String getITCLASSIFICACAOFISCAL() {
			return this.ITCLASSIFICACAOFISCAL;
		}

		public String ITSITTRIBUTARIAFEDERAL;

		public String getITSITTRIBUTARIAFEDERAL() {
			return this.ITSITTRIBUTARIAFEDERAL;
		}

		public String ITQTDEPRODUTO;

		public String getITQTDEPRODUTO() {
			return this.ITQTDEPRODUTO;
		}

		public String ITUNIDADEMEDIDA;

		public String getITUNIDADEMEDIDA() {
			return this.ITUNIDADEMEDIDA;
		}

		public String ITVALORPRECOUNITARIO;

		public String getITVALORPRECOUNITARIO() {
			return this.ITVALORPRECOUNITARIO;
		}

		public String ITVALORPRECOTOTAL;

		public String getITVALORPRECOTOTAL() {
			return this.ITVALORPRECOTOTAL;
		}

		public String ITVALORDESCONTO;

		public String getITVALORDESCONTO() {
			return this.ITVALORDESCONTO;
		}

		public String ITDESPESASFRETE;

		public String getITDESPESASFRETE() {
			return this.ITDESPESASFRETE;
		}

		public String ITDESPESASSEGURO;

		public String getITDESPESASSEGURO() {
			return this.ITDESPESASSEGURO;
		}

		public String ITOUTRASDESPESAS;

		public String getITOUTRASDESPESAS() {
			return this.ITOUTRASDESPESAS;
		}

		public String ITINDMOVIMENTACAO;

		public String getITINDMOVIMENTACAO() {
			return this.ITINDMOVIMENTACAO;
		}

		public String ITVALORBASEICMS;

		public String getITVALORBASEICMS() {
			return this.ITVALORBASEICMS;
		}

		public String ITALIQUOTAICMS;

		public String getITALIQUOTAICMS() {
			return this.ITALIQUOTAICMS;
		}

		public String ITVALORICMS;

		public String getITVALORICMS() {
			return this.ITVALORICMS;
		}

		public String ITVALORISENTOICM;

		public String getITVALORISENTOICM() {
			return this.ITVALORISENTOICM;
		}

		public String ITVALOROUTROICM;

		public String getITVALOROUTROICM() {
			return this.ITVALOROUTROICM;
		}

		public String ITVALORCONTABILICM;

		public String getITVALORCONTABILICM() {
			return this.ITVALORCONTABILICM;
		}

		public String FABASEPIS;

		public String getFABASEPIS() {
			return this.FABASEPIS;
		}

		public String ITBASEPIS;

		public String getITBASEPIS() {
			return this.ITBASEPIS;
		}

		public String ITALIQUOTAPIS;

		public String getITALIQUOTAPIS() {
			return this.ITALIQUOTAPIS;
		}

		public String ITVALORPIS;

		public String getITVALORPIS() {
			return this.ITVALORPIS;
		}

		public String FABASECOFINS;

		public String getFABASECOFINS() {
			return this.FABASECOFINS;
		}

		public String ITBASECOFINS;

		public String getITBASECOFINS() {
			return this.ITBASECOFINS;
		}

		public String ITALIQUOTACOFINS;

		public String getITALIQUOTACOFINS() {
			return this.ITALIQUOTACOFINS;
		}

		public String ITVALORCOFINS;

		public String getITVALORCOFINS() {
			return this.ITVALORCOFINS;
		}

		public String ITDESCRCOMPLEMENTAR;

		public String getITDESCRCOMPLEMENTAR() {
			return this.ITDESCRCOMPLEMENTAR;
		}

		public String ITPRECOTOTAL;

		public String getITPRECOTOTAL() {
			return this.ITPRECOTOTAL;
		}

		public String ITSITUACAOTRIBUTARIAESTADUAL;

		public String getITSITUACAOTRIBUTARIAESTADUAL() {
			return this.ITSITUACAOTRIBUTARIAESTADUAL;
		}

		public String ITSITTRIBCOFINS;

		public String getITSITTRIBCOFINS() {
			return this.ITSITTRIBCOFINS;
		}

		public String ITSITTRIBPIS;

		public String getITSITTRIBPIS() {
			return this.ITSITTRIBPIS;
		}

		public String ITCONTAANALITICA;

		public String getITCONTAANALITICA() {
			return this.ITCONTAANALITICA;
		}

		public String FAINDMOVIMENTO;

		public String getFAINDMOVIMENTO() {
			return this.FAINDMOVIMENTO;
		}

		public String ITUNIDADEPADRAO;

		public String getITUNIDADEPADRAO() {
			return this.ITUNIDADEPADRAO;
		}

		public String ITINDTRIBUTACAOIPI;

		public String getITINDTRIBUTACAOIPI() {
			return this.ITINDTRIBUTACAOIPI;
		}

		public String ITVALORCONTABILIPI;

		public String getITVALORCONTABILIPI() {
			return this.ITVALORCONTABILIPI;
		}

		public String ITVALORTOTALDOCUMENTO;

		public String getITVALORTOTALDOCUMENTO() {
			return this.ITVALORTOTALDOCUMENTO;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_client_conn_nfsproduto.length) {
					if (length < 1024
							&& commonByteArray_client_conn_nfsproduto.length == 0) {
						commonByteArray_client_conn_nfsproduto = new byte[1024];
					} else {
						commonByteArray_client_conn_nfsproduto = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_client_conn_nfsproduto, 0,
						length);
				strReturn = new String(
						commonByteArray_client_conn_nfsproduto, 0, length,
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

			synchronized (commonByteArrayLock_client_conn_nfsproduto) {

				try {

					int length = 0;

					this.FACODIGOEMPRESA = readString(dis);

					this.FAEMITENTE = readString(dis);

					this.FAMODELODOCUMENTO = readString(dis);

					this.FATIPODOCUMENTO = readString(dis);

					this.FASERIEDOCUMENTO = readString(dis);

					this.FANUMERODOCUMENTO = readString(dis);

					this.FANFECHAVE = readString(dis);

					this.FADATAEMISSAO = readDate(dis);

					this.FADATASAIDA = readDate(dis);

					this.FAHORASAIDA = readString(dis);

					this.FACODIGOCLIENTE = readString(dis);

					this.FAUFCLIENTE = readString(dis);

					this.FASITUACAODOC = readString(dis);

					this.FAINDCANCELAMENTO = readString(dis);

					this.FACONSUMIDOR = readString(dis);

					this.FACONTRIBUINTE = readString(dis);

					this.FAVALORPRODUTOS = readString(dis);

					this.FATIPOFATURA = readString(dis);

					this.FATOTALNFISCAL = readString(dis);

					this.FABASEICMS = readString(dis);

					this.FATOTALICM = readString(dis);

					this.FABASEREDUCAOIPI = readString(dis);

					this.FAVALORTOTALIPI = readString(dis);

					this.FATOTALBASESUBST = readString(dis);

					this.FATOTALICMSUBST = readString(dis);

					this.FAINSCRICAOSUBSTITUTO = readString(dis);

					this.FAVALORFRETE = readString(dis);

					this.FAVALORSEGURO = readString(dis);

					this.FAVALOROUTRAS = readString(dis);

					this.FAVIATRANSPORTE = readString(dis);

					this.FAMODALIDADEFRETE = readString(dis);

					this.FACODIGOTRANSPORTADOR = readString(dis);

					this.FAQTDEVOLUMES = readString(dis);

					this.FAESPECIEVOLUMES = readString(dis);

					this.FAMARCAVOLUMES = readString(dis);

					this.FANUMERACAOVOLUMES = readString(dis);

					this.FAPESOBRUTO = readString(dis);

					this.FAPESOLIQUIDO = readString(dis);

					this.FAIDENTIFICACAOVEICULO = readString(dis);

					this.FATIPONOTA = readString(dis);

					this.ITNUMEROITEM = readString(dis);

					this.ITCODIGOPRODUTO = readString(dis);

					this.ITCFOP = readString(dis);

					this.ITCLASSIFICACAOFISCAL = readString(dis);

					this.ITSITTRIBUTARIAFEDERAL = readString(dis);

					this.ITQTDEPRODUTO = readString(dis);

					this.ITUNIDADEMEDIDA = readString(dis);

					this.ITVALORPRECOUNITARIO = readString(dis);

					this.ITVALORPRECOTOTAL = readString(dis);

					this.ITVALORDESCONTO = readString(dis);

					this.ITDESPESASFRETE = readString(dis);

					this.ITDESPESASSEGURO = readString(dis);

					this.ITOUTRASDESPESAS = readString(dis);

					this.ITINDMOVIMENTACAO = readString(dis);

					this.ITVALORBASEICMS = readString(dis);

					this.ITALIQUOTAICMS = readString(dis);

					this.ITVALORICMS = readString(dis);

					this.ITVALORISENTOICM = readString(dis);

					this.ITVALOROUTROICM = readString(dis);

					this.ITVALORCONTABILICM = readString(dis);

					this.FABASEPIS = readString(dis);

					this.ITBASEPIS = readString(dis);

					this.ITALIQUOTAPIS = readString(dis);

					this.ITVALORPIS = readString(dis);

					this.FABASECOFINS = readString(dis);

					this.ITBASECOFINS = readString(dis);

					this.ITALIQUOTACOFINS = readString(dis);

					this.ITVALORCOFINS = readString(dis);

					this.ITDESCRCOMPLEMENTAR = readString(dis);

					this.ITPRECOTOTAL = readString(dis);

					this.ITSITUACAOTRIBUTARIAESTADUAL = readString(dis);

					this.ITSITTRIBCOFINS = readString(dis);

					this.ITSITTRIBPIS = readString(dis);

					this.ITCONTAANALITICA = readString(dis);

					this.FAINDMOVIMENTO = readString(dis);

					this.ITUNIDADEPADRAO = readString(dis);

					this.ITINDTRIBUTACAOIPI = readString(dis);

					this.ITVALORCONTABILIPI = readString(dis);

					this.ITVALORTOTALDOCUMENTO = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.FACODIGOEMPRESA, dos);

				// String

				writeString(this.FAEMITENTE, dos);

				// String

				writeString(this.FAMODELODOCUMENTO, dos);

				// String

				writeString(this.FATIPODOCUMENTO, dos);

				// String

				writeString(this.FASERIEDOCUMENTO, dos);

				// String

				writeString(this.FANUMERODOCUMENTO, dos);

				// String

				writeString(this.FANFECHAVE, dos);

				// java.util.Date

				writeDate(this.FADATAEMISSAO, dos);

				// java.util.Date

				writeDate(this.FADATASAIDA, dos);

				// String

				writeString(this.FAHORASAIDA, dos);

				// String

				writeString(this.FACODIGOCLIENTE, dos);

				// String

				writeString(this.FAUFCLIENTE, dos);

				// String

				writeString(this.FASITUACAODOC, dos);

				// String

				writeString(this.FAINDCANCELAMENTO, dos);

				// String

				writeString(this.FACONSUMIDOR, dos);

				// String

				writeString(this.FACONTRIBUINTE, dos);

				// String

				writeString(this.FAVALORPRODUTOS, dos);

				// String

				writeString(this.FATIPOFATURA, dos);

				// String

				writeString(this.FATOTALNFISCAL, dos);

				// String

				writeString(this.FABASEICMS, dos);

				// String

				writeString(this.FATOTALICM, dos);

				// String

				writeString(this.FABASEREDUCAOIPI, dos);

				// String

				writeString(this.FAVALORTOTALIPI, dos);

				// String

				writeString(this.FATOTALBASESUBST, dos);

				// String

				writeString(this.FATOTALICMSUBST, dos);

				// String

				writeString(this.FAINSCRICAOSUBSTITUTO, dos);

				// String

				writeString(this.FAVALORFRETE, dos);

				// String

				writeString(this.FAVALORSEGURO, dos);

				// String

				writeString(this.FAVALOROUTRAS, dos);

				// String

				writeString(this.FAVIATRANSPORTE, dos);

				// String

				writeString(this.FAMODALIDADEFRETE, dos);

				// String

				writeString(this.FACODIGOTRANSPORTADOR, dos);

				// String

				writeString(this.FAQTDEVOLUMES, dos);

				// String

				writeString(this.FAESPECIEVOLUMES, dos);

				// String

				writeString(this.FAMARCAVOLUMES, dos);

				// String

				writeString(this.FANUMERACAOVOLUMES, dos);

				// String

				writeString(this.FAPESOBRUTO, dos);

				// String

				writeString(this.FAPESOLIQUIDO, dos);

				// String

				writeString(this.FAIDENTIFICACAOVEICULO, dos);

				// String

				writeString(this.FATIPONOTA, dos);

				// String

				writeString(this.ITNUMEROITEM, dos);

				// String

				writeString(this.ITCODIGOPRODUTO, dos);

				// String

				writeString(this.ITCFOP, dos);

				// String

				writeString(this.ITCLASSIFICACAOFISCAL, dos);

				// String

				writeString(this.ITSITTRIBUTARIAFEDERAL, dos);

				// String

				writeString(this.ITQTDEPRODUTO, dos);

				// String

				writeString(this.ITUNIDADEMEDIDA, dos);

				// String

				writeString(this.ITVALORPRECOUNITARIO, dos);

				// String

				writeString(this.ITVALORPRECOTOTAL, dos);

				// String

				writeString(this.ITVALORDESCONTO, dos);

				// String

				writeString(this.ITDESPESASFRETE, dos);

				// String

				writeString(this.ITDESPESASSEGURO, dos);

				// String

				writeString(this.ITOUTRASDESPESAS, dos);

				// String

				writeString(this.ITINDMOVIMENTACAO, dos);

				// String

				writeString(this.ITVALORBASEICMS, dos);

				// String

				writeString(this.ITALIQUOTAICMS, dos);

				// String

				writeString(this.ITVALORICMS, dos);

				// String

				writeString(this.ITVALORISENTOICM, dos);

				// String

				writeString(this.ITVALOROUTROICM, dos);

				// String

				writeString(this.ITVALORCONTABILICM, dos);

				// String

				writeString(this.FABASEPIS, dos);

				// String

				writeString(this.ITBASEPIS, dos);

				// String

				writeString(this.ITALIQUOTAPIS, dos);

				// String

				writeString(this.ITVALORPIS, dos);

				// String

				writeString(this.FABASECOFINS, dos);

				// String

				writeString(this.ITBASECOFINS, dos);

				// String

				writeString(this.ITALIQUOTACOFINS, dos);

				// String

				writeString(this.ITVALORCOFINS, dos);

				// String

				writeString(this.ITDESCRCOMPLEMENTAR, dos);

				// String

				writeString(this.ITPRECOTOTAL, dos);

				// String

				writeString(this.ITSITUACAOTRIBUTARIAESTADUAL, dos);

				// String

				writeString(this.ITSITTRIBCOFINS, dos);

				// String

				writeString(this.ITSITTRIBPIS, dos);

				// String

				writeString(this.ITCONTAANALITICA, dos);

				// String

				writeString(this.FAINDMOVIMENTO, dos);

				// String

				writeString(this.ITUNIDADEPADRAO, dos);

				// String

				writeString(this.ITINDTRIBUTACAOIPI, dos);

				// String

				writeString(this.ITVALORCONTABILIPI, dos);

				// String

				writeString(this.ITVALORTOTALDOCUMENTO, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("FACODIGOEMPRESA=" + FACODIGOEMPRESA);
			sb.append(",FAEMITENTE=" + FAEMITENTE);
			sb.append(",FAMODELODOCUMENTO=" + FAMODELODOCUMENTO);
			sb.append(",FATIPODOCUMENTO=" + FATIPODOCUMENTO);
			sb.append(",FASERIEDOCUMENTO=" + FASERIEDOCUMENTO);
			sb.append(",FANUMERODOCUMENTO=" + FANUMERODOCUMENTO);
			sb.append(",FANFECHAVE=" + FANFECHAVE);
			sb.append(",FADATAEMISSAO=" + String.valueOf(FADATAEMISSAO));
			sb.append(",FADATASAIDA=" + String.valueOf(FADATASAIDA));
			sb.append(",FAHORASAIDA=" + FAHORASAIDA);
			sb.append(",FACODIGOCLIENTE=" + FACODIGOCLIENTE);
			sb.append(",FAUFCLIENTE=" + FAUFCLIENTE);
			sb.append(",FASITUACAODOC=" + FASITUACAODOC);
			sb.append(",FAINDCANCELAMENTO=" + FAINDCANCELAMENTO);
			sb.append(",FACONSUMIDOR=" + FACONSUMIDOR);
			sb.append(",FACONTRIBUINTE=" + FACONTRIBUINTE);
			sb.append(",FAVALORPRODUTOS=" + FAVALORPRODUTOS);
			sb.append(",FATIPOFATURA=" + FATIPOFATURA);
			sb.append(",FATOTALNFISCAL=" + FATOTALNFISCAL);
			sb.append(",FABASEICMS=" + FABASEICMS);
			sb.append(",FATOTALICM=" + FATOTALICM);
			sb.append(",FABASEREDUCAOIPI=" + FABASEREDUCAOIPI);
			sb.append(",FAVALORTOTALIPI=" + FAVALORTOTALIPI);
			sb.append(",FATOTALBASESUBST=" + FATOTALBASESUBST);
			sb.append(",FATOTALICMSUBST=" + FATOTALICMSUBST);
			sb.append(",FAINSCRICAOSUBSTITUTO=" + FAINSCRICAOSUBSTITUTO);
			sb.append(",FAVALORFRETE=" + FAVALORFRETE);
			sb.append(",FAVALORSEGURO=" + FAVALORSEGURO);
			sb.append(",FAVALOROUTRAS=" + FAVALOROUTRAS);
			sb.append(",FAVIATRANSPORTE=" + FAVIATRANSPORTE);
			sb.append(",FAMODALIDADEFRETE=" + FAMODALIDADEFRETE);
			sb.append(",FACODIGOTRANSPORTADOR=" + FACODIGOTRANSPORTADOR);
			sb.append(",FAQTDEVOLUMES=" + FAQTDEVOLUMES);
			sb.append(",FAESPECIEVOLUMES=" + FAESPECIEVOLUMES);
			sb.append(",FAMARCAVOLUMES=" + FAMARCAVOLUMES);
			sb.append(",FANUMERACAOVOLUMES=" + FANUMERACAOVOLUMES);
			sb.append(",FAPESOBRUTO=" + FAPESOBRUTO);
			sb.append(",FAPESOLIQUIDO=" + FAPESOLIQUIDO);
			sb.append(",FAIDENTIFICACAOVEICULO=" + FAIDENTIFICACAOVEICULO);
			sb.append(",FATIPONOTA=" + FATIPONOTA);
			sb.append(",ITNUMEROITEM=" + ITNUMEROITEM);
			sb.append(",ITCODIGOPRODUTO=" + ITCODIGOPRODUTO);
			sb.append(",ITCFOP=" + ITCFOP);
			sb.append(",ITCLASSIFICACAOFISCAL=" + ITCLASSIFICACAOFISCAL);
			sb.append(",ITSITTRIBUTARIAFEDERAL=" + ITSITTRIBUTARIAFEDERAL);
			sb.append(",ITQTDEPRODUTO=" + ITQTDEPRODUTO);
			sb.append(",ITUNIDADEMEDIDA=" + ITUNIDADEMEDIDA);
			sb.append(",ITVALORPRECOUNITARIO=" + ITVALORPRECOUNITARIO);
			sb.append(",ITVALORPRECOTOTAL=" + ITVALORPRECOTOTAL);
			sb.append(",ITVALORDESCONTO=" + ITVALORDESCONTO);
			sb.append(",ITDESPESASFRETE=" + ITDESPESASFRETE);
			sb.append(",ITDESPESASSEGURO=" + ITDESPESASSEGURO);
			sb.append(",ITOUTRASDESPESAS=" + ITOUTRASDESPESAS);
			sb.append(",ITINDMOVIMENTACAO=" + ITINDMOVIMENTACAO);
			sb.append(",ITVALORBASEICMS=" + ITVALORBASEICMS);
			sb.append(",ITALIQUOTAICMS=" + ITALIQUOTAICMS);
			sb.append(",ITVALORICMS=" + ITVALORICMS);
			sb.append(",ITVALORISENTOICM=" + ITVALORISENTOICM);
			sb.append(",ITVALOROUTROICM=" + ITVALOROUTROICM);
			sb.append(",ITVALORCONTABILICM=" + ITVALORCONTABILICM);
			sb.append(",FABASEPIS=" + FABASEPIS);
			sb.append(",ITBASEPIS=" + ITBASEPIS);
			sb.append(",ITALIQUOTAPIS=" + ITALIQUOTAPIS);
			sb.append(",ITVALORPIS=" + ITVALORPIS);
			sb.append(",FABASECOFINS=" + FABASECOFINS);
			sb.append(",ITBASECOFINS=" + ITBASECOFINS);
			sb.append(",ITALIQUOTACOFINS=" + ITALIQUOTACOFINS);
			sb.append(",ITVALORCOFINS=" + ITVALORCOFINS);
			sb.append(",ITDESCRCOMPLEMENTAR=" + ITDESCRCOMPLEMENTAR);
			sb.append(",ITPRECOTOTAL=" + ITPRECOTOTAL);
			sb.append(",ITSITUACAOTRIBUTARIAESTADUAL="
					+ ITSITUACAOTRIBUTARIAESTADUAL);
			sb.append(",ITSITTRIBCOFINS=" + ITSITTRIBCOFINS);
			sb.append(",ITSITTRIBPIS=" + ITSITTRIBPIS);
			sb.append(",ITCONTAANALITICA=" + ITCONTAANALITICA);
			sb.append(",FAINDMOVIMENTO=" + FAINDMOVIMENTO);
			sb.append(",ITUNIDADEPADRAO=" + ITUNIDADEPADRAO);
			sb.append(",ITINDTRIBUTACAOIPI=" + ITINDTRIBUTACAOIPI);
			sb.append(",ITVALORCONTABILIPI=" + ITVALORCONTABILIPI);
			sb.append(",ITVALORTOTALDOCUMENTO=" + ITVALORTOTALDOCUMENTO);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row3Struct other) {

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

	public static class row2Struct implements
			routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_client_conn_nfsproduto = new byte[0];
		static byte[] commonByteArray_client_conn_nfsproduto = new byte[0];

		public String FACODIGOEMPRESA;

		public String getFACODIGOEMPRESA() {
			return this.FACODIGOEMPRESA;
		}

		public String FAEMITENTE;

		public String getFAEMITENTE() {
			return this.FAEMITENTE;
		}

		public String FAMODELODOCUMENTO;

		public String getFAMODELODOCUMENTO() {
			return this.FAMODELODOCUMENTO;
		}

		public String FATIPODOCUMENTO;

		public String getFATIPODOCUMENTO() {
			return this.FATIPODOCUMENTO;
		}

		public String FASERIEDOCUMENTO;

		public String getFASERIEDOCUMENTO() {
			return this.FASERIEDOCUMENTO;
		}

		public String FANUMERODOCUMENTO;

		public String getFANUMERODOCUMENTO() {
			return this.FANUMERODOCUMENTO;
		}

		public String FANFECHAVE;

		public String getFANFECHAVE() {
			return this.FANFECHAVE;
		}

		public java.util.Date FADATAEMISSAO;

		public java.util.Date getFADATAEMISSAO() {
			return this.FADATAEMISSAO;
		}

		public java.util.Date FADATASAIDA;

		public java.util.Date getFADATASAIDA() {
			return this.FADATASAIDA;
		}

		public String FAHORASAIDA;

		public String getFAHORASAIDA() {
			return this.FAHORASAIDA;
		}

		public String FACODIGOCLIENTE;

		public String getFACODIGOCLIENTE() {
			return this.FACODIGOCLIENTE;
		}

		public String FAUFCLIENTE;

		public String getFAUFCLIENTE() {
			return this.FAUFCLIENTE;
		}

		public String FASITUACAODOC;

		public String getFASITUACAODOC() {
			return this.FASITUACAODOC;
		}

		public String FAINDCANCELAMENTO;

		public String getFAINDCANCELAMENTO() {
			return this.FAINDCANCELAMENTO;
		}

		public String FACONSUMIDOR;

		public String getFACONSUMIDOR() {
			return this.FACONSUMIDOR;
		}

		public String FACONTRIBUINTE;

		public String getFACONTRIBUINTE() {
			return this.FACONTRIBUINTE;
		}

		public String FAVALORPRODUTOS;

		public String getFAVALORPRODUTOS() {
			return this.FAVALORPRODUTOS;
		}

		public String FATIPOFATURA;

		public String getFATIPOFATURA() {
			return this.FATIPOFATURA;
		}

		public String FATOTALNFISCAL;

		public String getFATOTALNFISCAL() {
			return this.FATOTALNFISCAL;
		}

		public String FABASEICMS;

		public String getFABASEICMS() {
			return this.FABASEICMS;
		}

		public String FATOTALICM;

		public String getFATOTALICM() {
			return this.FATOTALICM;
		}

		public String FABASEREDUCAOIPI;

		public String getFABASEREDUCAOIPI() {
			return this.FABASEREDUCAOIPI;
		}

		public String FAVALORTOTALIPI;

		public String getFAVALORTOTALIPI() {
			return this.FAVALORTOTALIPI;
		}

		public String FATOTALBASESUBST;

		public String getFATOTALBASESUBST() {
			return this.FATOTALBASESUBST;
		}

		public String FATOTALICMSUBST;

		public String getFATOTALICMSUBST() {
			return this.FATOTALICMSUBST;
		}

		public String FAINSCRICAOSUBSTITUTO;

		public String getFAINSCRICAOSUBSTITUTO() {
			return this.FAINSCRICAOSUBSTITUTO;
		}

		public String FAVALORFRETE;

		public String getFAVALORFRETE() {
			return this.FAVALORFRETE;
		}

		public String FAVALORSEGURO;

		public String getFAVALORSEGURO() {
			return this.FAVALORSEGURO;
		}

		public String FAVALOROUTRAS;

		public String getFAVALOROUTRAS() {
			return this.FAVALOROUTRAS;
		}

		public String FAVIATRANSPORTE;

		public String getFAVIATRANSPORTE() {
			return this.FAVIATRANSPORTE;
		}

		public String FAMODALIDADEFRETE;

		public String getFAMODALIDADEFRETE() {
			return this.FAMODALIDADEFRETE;
		}

		public String FACODIGOTRANSPORTADOR;

		public String getFACODIGOTRANSPORTADOR() {
			return this.FACODIGOTRANSPORTADOR;
		}

		public String FAQTDEVOLUMES;

		public String getFAQTDEVOLUMES() {
			return this.FAQTDEVOLUMES;
		}

		public String FAESPECIEVOLUMES;

		public String getFAESPECIEVOLUMES() {
			return this.FAESPECIEVOLUMES;
		}

		public String FAMARCAVOLUMES;

		public String getFAMARCAVOLUMES() {
			return this.FAMARCAVOLUMES;
		}

		public String FANUMERACAOVOLUMES;

		public String getFANUMERACAOVOLUMES() {
			return this.FANUMERACAOVOLUMES;
		}

		public String FAPESOBRUTO;

		public String getFAPESOBRUTO() {
			return this.FAPESOBRUTO;
		}

		public String FAPESOLIQUIDO;

		public String getFAPESOLIQUIDO() {
			return this.FAPESOLIQUIDO;
		}

		public String FAIDENTIFICACAOVEICULO;

		public String getFAIDENTIFICACAOVEICULO() {
			return this.FAIDENTIFICACAOVEICULO;
		}

		public String FATIPONOTA;

		public String getFATIPONOTA() {
			return this.FATIPONOTA;
		}

		public String ITNUMEROITEM;

		public String getITNUMEROITEM() {
			return this.ITNUMEROITEM;
		}

		public String ITCODIGOPRODUTO;

		public String getITCODIGOPRODUTO() {
			return this.ITCODIGOPRODUTO;
		}

		public String ITCFOP;

		public String getITCFOP() {
			return this.ITCFOP;
		}

		public String ITCLASSIFICACAOFISCAL;

		public String getITCLASSIFICACAOFISCAL() {
			return this.ITCLASSIFICACAOFISCAL;
		}

		public String ITSITTRIBUTARIAFEDERAL;

		public String getITSITTRIBUTARIAFEDERAL() {
			return this.ITSITTRIBUTARIAFEDERAL;
		}

		public String ITQTDEPRODUTO;

		public String getITQTDEPRODUTO() {
			return this.ITQTDEPRODUTO;
		}

		public String ITUNIDADEMEDIDA;

		public String getITUNIDADEMEDIDA() {
			return this.ITUNIDADEMEDIDA;
		}

		public String ITVALORPRECOUNITARIO;

		public String getITVALORPRECOUNITARIO() {
			return this.ITVALORPRECOUNITARIO;
		}

		public String ITVALORPRECOTOTAL;

		public String getITVALORPRECOTOTAL() {
			return this.ITVALORPRECOTOTAL;
		}

		public String ITVALORDESCONTO;

		public String getITVALORDESCONTO() {
			return this.ITVALORDESCONTO;
		}

		public String ITDESPESASFRETE;

		public String getITDESPESASFRETE() {
			return this.ITDESPESASFRETE;
		}

		public String ITDESPESASSEGURO;

		public String getITDESPESASSEGURO() {
			return this.ITDESPESASSEGURO;
		}

		public String ITOUTRASDESPESAS;

		public String getITOUTRASDESPESAS() {
			return this.ITOUTRASDESPESAS;
		}

		public String ITINDMOVIMENTACAO;

		public String getITINDMOVIMENTACAO() {
			return this.ITINDMOVIMENTACAO;
		}

		public String ITVALORBASEICMS;

		public String getITVALORBASEICMS() {
			return this.ITVALORBASEICMS;
		}

		public String ITALIQUOTAICMS;

		public String getITALIQUOTAICMS() {
			return this.ITALIQUOTAICMS;
		}

		public String ITVALORICMS;

		public String getITVALORICMS() {
			return this.ITVALORICMS;
		}

		public String ITVALORISENTOICM;

		public String getITVALORISENTOICM() {
			return this.ITVALORISENTOICM;
		}

		public String ITVALOROUTROICM;

		public String getITVALOROUTROICM() {
			return this.ITVALOROUTROICM;
		}

		public String ITVALORCONTABILICM;

		public String getITVALORCONTABILICM() {
			return this.ITVALORCONTABILICM;
		}

		public String FABASEPIS;

		public String getFABASEPIS() {
			return this.FABASEPIS;
		}

		public String ITBASEPIS;

		public String getITBASEPIS() {
			return this.ITBASEPIS;
		}

		public String ITALIQUOTAPIS;

		public String getITALIQUOTAPIS() {
			return this.ITALIQUOTAPIS;
		}

		public String ITVALORPIS;

		public String getITVALORPIS() {
			return this.ITVALORPIS;
		}

		public String FABASECOFINS;

		public String getFABASECOFINS() {
			return this.FABASECOFINS;
		}

		public String ITBASECOFINS;

		public String getITBASECOFINS() {
			return this.ITBASECOFINS;
		}

		public String ITALIQUOTACOFINS;

		public String getITALIQUOTACOFINS() {
			return this.ITALIQUOTACOFINS;
		}

		public String ITVALORCOFINS;

		public String getITVALORCOFINS() {
			return this.ITVALORCOFINS;
		}

		public String ITDESCRCOMPLEMENTAR;

		public String getITDESCRCOMPLEMENTAR() {
			return this.ITDESCRCOMPLEMENTAR;
		}

		public String ITPRECOTOTAL;

		public String getITPRECOTOTAL() {
			return this.ITPRECOTOTAL;
		}

		public String ITSITUACAOTRIBUTARIAESTADUAL;

		public String getITSITUACAOTRIBUTARIAESTADUAL() {
			return this.ITSITUACAOTRIBUTARIAESTADUAL;
		}

		public String ITSITTRIBCOFINS;

		public String getITSITTRIBCOFINS() {
			return this.ITSITTRIBCOFINS;
		}

		public String ITSITTRIBPIS;

		public String getITSITTRIBPIS() {
			return this.ITSITTRIBPIS;
		}

		public String ITCONTAANALITICA;

		public String getITCONTAANALITICA() {
			return this.ITCONTAANALITICA;
		}

		public String FAINDMOVIMENTO;

		public String getFAINDMOVIMENTO() {
			return this.FAINDMOVIMENTO;
		}

		public String ITUNIDADEPADRAO;

		public String getITUNIDADEPADRAO() {
			return this.ITUNIDADEPADRAO;
		}

		public String ITINDTRIBUTACAOIPI;

		public String getITINDTRIBUTACAOIPI() {
			return this.ITINDTRIBUTACAOIPI;
		}

		public String ITVALORCONTABILIPI;

		public String getITVALORCONTABILIPI() {
			return this.ITVALORCONTABILIPI;
		}

		public String ITVALORTOTALDOCUMENTO;

		public String getITVALORTOTALDOCUMENTO() {
			return this.ITVALORTOTALDOCUMENTO;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_client_conn_nfsproduto.length) {
					if (length < 1024
							&& commonByteArray_client_conn_nfsproduto.length == 0) {
						commonByteArray_client_conn_nfsproduto = new byte[1024];
					} else {
						commonByteArray_client_conn_nfsproduto = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_client_conn_nfsproduto, 0,
						length);
				strReturn = new String(
						commonByteArray_client_conn_nfsproduto, 0, length,
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

			synchronized (commonByteArrayLock_client_conn_nfsproduto) {

				try {

					int length = 0;

					this.FACODIGOEMPRESA = readString(dis);

					this.FAEMITENTE = readString(dis);

					this.FAMODELODOCUMENTO = readString(dis);

					this.FATIPODOCUMENTO = readString(dis);

					this.FASERIEDOCUMENTO = readString(dis);

					this.FANUMERODOCUMENTO = readString(dis);

					this.FANFECHAVE = readString(dis);

					this.FADATAEMISSAO = readDate(dis);

					this.FADATASAIDA = readDate(dis);

					this.FAHORASAIDA = readString(dis);

					this.FACODIGOCLIENTE = readString(dis);

					this.FAUFCLIENTE = readString(dis);

					this.FASITUACAODOC = readString(dis);

					this.FAINDCANCELAMENTO = readString(dis);

					this.FACONSUMIDOR = readString(dis);

					this.FACONTRIBUINTE = readString(dis);

					this.FAVALORPRODUTOS = readString(dis);

					this.FATIPOFATURA = readString(dis);

					this.FATOTALNFISCAL = readString(dis);

					this.FABASEICMS = readString(dis);

					this.FATOTALICM = readString(dis);

					this.FABASEREDUCAOIPI = readString(dis);

					this.FAVALORTOTALIPI = readString(dis);

					this.FATOTALBASESUBST = readString(dis);

					this.FATOTALICMSUBST = readString(dis);

					this.FAINSCRICAOSUBSTITUTO = readString(dis);

					this.FAVALORFRETE = readString(dis);

					this.FAVALORSEGURO = readString(dis);

					this.FAVALOROUTRAS = readString(dis);

					this.FAVIATRANSPORTE = readString(dis);

					this.FAMODALIDADEFRETE = readString(dis);

					this.FACODIGOTRANSPORTADOR = readString(dis);

					this.FAQTDEVOLUMES = readString(dis);

					this.FAESPECIEVOLUMES = readString(dis);

					this.FAMARCAVOLUMES = readString(dis);

					this.FANUMERACAOVOLUMES = readString(dis);

					this.FAPESOBRUTO = readString(dis);

					this.FAPESOLIQUIDO = readString(dis);

					this.FAIDENTIFICACAOVEICULO = readString(dis);

					this.FATIPONOTA = readString(dis);

					this.ITNUMEROITEM = readString(dis);

					this.ITCODIGOPRODUTO = readString(dis);

					this.ITCFOP = readString(dis);

					this.ITCLASSIFICACAOFISCAL = readString(dis);

					this.ITSITTRIBUTARIAFEDERAL = readString(dis);

					this.ITQTDEPRODUTO = readString(dis);

					this.ITUNIDADEMEDIDA = readString(dis);

					this.ITVALORPRECOUNITARIO = readString(dis);

					this.ITVALORPRECOTOTAL = readString(dis);

					this.ITVALORDESCONTO = readString(dis);

					this.ITDESPESASFRETE = readString(dis);

					this.ITDESPESASSEGURO = readString(dis);

					this.ITOUTRASDESPESAS = readString(dis);

					this.ITINDMOVIMENTACAO = readString(dis);

					this.ITVALORBASEICMS = readString(dis);

					this.ITALIQUOTAICMS = readString(dis);

					this.ITVALORICMS = readString(dis);

					this.ITVALORISENTOICM = readString(dis);

					this.ITVALOROUTROICM = readString(dis);

					this.ITVALORCONTABILICM = readString(dis);

					this.FABASEPIS = readString(dis);

					this.ITBASEPIS = readString(dis);

					this.ITALIQUOTAPIS = readString(dis);

					this.ITVALORPIS = readString(dis);

					this.FABASECOFINS = readString(dis);

					this.ITBASECOFINS = readString(dis);

					this.ITALIQUOTACOFINS = readString(dis);

					this.ITVALORCOFINS = readString(dis);

					this.ITDESCRCOMPLEMENTAR = readString(dis);

					this.ITPRECOTOTAL = readString(dis);

					this.ITSITUACAOTRIBUTARIAESTADUAL = readString(dis);

					this.ITSITTRIBCOFINS = readString(dis);

					this.ITSITTRIBPIS = readString(dis);

					this.ITCONTAANALITICA = readString(dis);

					this.FAINDMOVIMENTO = readString(dis);

					this.ITUNIDADEPADRAO = readString(dis);

					this.ITINDTRIBUTACAOIPI = readString(dis);

					this.ITVALORCONTABILIPI = readString(dis);

					this.ITVALORTOTALDOCUMENTO = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.FACODIGOEMPRESA, dos);

				// String

				writeString(this.FAEMITENTE, dos);

				// String

				writeString(this.FAMODELODOCUMENTO, dos);

				// String

				writeString(this.FATIPODOCUMENTO, dos);

				// String

				writeString(this.FASERIEDOCUMENTO, dos);

				// String

				writeString(this.FANUMERODOCUMENTO, dos);

				// String

				writeString(this.FANFECHAVE, dos);

				// java.util.Date

				writeDate(this.FADATAEMISSAO, dos);

				// java.util.Date

				writeDate(this.FADATASAIDA, dos);

				// String

				writeString(this.FAHORASAIDA, dos);

				// String

				writeString(this.FACODIGOCLIENTE, dos);

				// String

				writeString(this.FAUFCLIENTE, dos);

				// String

				writeString(this.FASITUACAODOC, dos);

				// String

				writeString(this.FAINDCANCELAMENTO, dos);

				// String

				writeString(this.FACONSUMIDOR, dos);

				// String

				writeString(this.FACONTRIBUINTE, dos);

				// String

				writeString(this.FAVALORPRODUTOS, dos);

				// String

				writeString(this.FATIPOFATURA, dos);

				// String

				writeString(this.FATOTALNFISCAL, dos);

				// String

				writeString(this.FABASEICMS, dos);

				// String

				writeString(this.FATOTALICM, dos);

				// String

				writeString(this.FABASEREDUCAOIPI, dos);

				// String

				writeString(this.FAVALORTOTALIPI, dos);

				// String

				writeString(this.FATOTALBASESUBST, dos);

				// String

				writeString(this.FATOTALICMSUBST, dos);

				// String

				writeString(this.FAINSCRICAOSUBSTITUTO, dos);

				// String

				writeString(this.FAVALORFRETE, dos);

				// String

				writeString(this.FAVALORSEGURO, dos);

				// String

				writeString(this.FAVALOROUTRAS, dos);

				// String

				writeString(this.FAVIATRANSPORTE, dos);

				// String

				writeString(this.FAMODALIDADEFRETE, dos);

				// String

				writeString(this.FACODIGOTRANSPORTADOR, dos);

				// String

				writeString(this.FAQTDEVOLUMES, dos);

				// String

				writeString(this.FAESPECIEVOLUMES, dos);

				// String

				writeString(this.FAMARCAVOLUMES, dos);

				// String

				writeString(this.FANUMERACAOVOLUMES, dos);

				// String

				writeString(this.FAPESOBRUTO, dos);

				// String

				writeString(this.FAPESOLIQUIDO, dos);

				// String

				writeString(this.FAIDENTIFICACAOVEICULO, dos);

				// String

				writeString(this.FATIPONOTA, dos);

				// String

				writeString(this.ITNUMEROITEM, dos);

				// String

				writeString(this.ITCODIGOPRODUTO, dos);

				// String

				writeString(this.ITCFOP, dos);

				// String

				writeString(this.ITCLASSIFICACAOFISCAL, dos);

				// String

				writeString(this.ITSITTRIBUTARIAFEDERAL, dos);

				// String

				writeString(this.ITQTDEPRODUTO, dos);

				// String

				writeString(this.ITUNIDADEMEDIDA, dos);

				// String

				writeString(this.ITVALORPRECOUNITARIO, dos);

				// String

				writeString(this.ITVALORPRECOTOTAL, dos);

				// String

				writeString(this.ITVALORDESCONTO, dos);

				// String

				writeString(this.ITDESPESASFRETE, dos);

				// String

				writeString(this.ITDESPESASSEGURO, dos);

				// String

				writeString(this.ITOUTRASDESPESAS, dos);

				// String

				writeString(this.ITINDMOVIMENTACAO, dos);

				// String

				writeString(this.ITVALORBASEICMS, dos);

				// String

				writeString(this.ITALIQUOTAICMS, dos);

				// String

				writeString(this.ITVALORICMS, dos);

				// String

				writeString(this.ITVALORISENTOICM, dos);

				// String

				writeString(this.ITVALOROUTROICM, dos);

				// String

				writeString(this.ITVALORCONTABILICM, dos);

				// String

				writeString(this.FABASEPIS, dos);

				// String

				writeString(this.ITBASEPIS, dos);

				// String

				writeString(this.ITALIQUOTAPIS, dos);

				// String

				writeString(this.ITVALORPIS, dos);

				// String

				writeString(this.FABASECOFINS, dos);

				// String

				writeString(this.ITBASECOFINS, dos);

				// String

				writeString(this.ITALIQUOTACOFINS, dos);

				// String

				writeString(this.ITVALORCOFINS, dos);

				// String

				writeString(this.ITDESCRCOMPLEMENTAR, dos);

				// String

				writeString(this.ITPRECOTOTAL, dos);

				// String

				writeString(this.ITSITUACAOTRIBUTARIAESTADUAL, dos);

				// String

				writeString(this.ITSITTRIBCOFINS, dos);

				// String

				writeString(this.ITSITTRIBPIS, dos);

				// String

				writeString(this.ITCONTAANALITICA, dos);

				// String

				writeString(this.FAINDMOVIMENTO, dos);

				// String

				writeString(this.ITUNIDADEPADRAO, dos);

				// String

				writeString(this.ITINDTRIBUTACAOIPI, dos);

				// String

				writeString(this.ITVALORCONTABILIPI, dos);

				// String

				writeString(this.ITVALORTOTALDOCUMENTO, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("FACODIGOEMPRESA=" + FACODIGOEMPRESA);
			sb.append(",FAEMITENTE=" + FAEMITENTE);
			sb.append(",FAMODELODOCUMENTO=" + FAMODELODOCUMENTO);
			sb.append(",FATIPODOCUMENTO=" + FATIPODOCUMENTO);
			sb.append(",FASERIEDOCUMENTO=" + FASERIEDOCUMENTO);
			sb.append(",FANUMERODOCUMENTO=" + FANUMERODOCUMENTO);
			sb.append(",FANFECHAVE=" + FANFECHAVE);
			sb.append(",FADATAEMISSAO=" + String.valueOf(FADATAEMISSAO));
			sb.append(",FADATASAIDA=" + String.valueOf(FADATASAIDA));
			sb.append(",FAHORASAIDA=" + FAHORASAIDA);
			sb.append(",FACODIGOCLIENTE=" + FACODIGOCLIENTE);
			sb.append(",FAUFCLIENTE=" + FAUFCLIENTE);
			sb.append(",FASITUACAODOC=" + FASITUACAODOC);
			sb.append(",FAINDCANCELAMENTO=" + FAINDCANCELAMENTO);
			sb.append(",FACONSUMIDOR=" + FACONSUMIDOR);
			sb.append(",FACONTRIBUINTE=" + FACONTRIBUINTE);
			sb.append(",FAVALORPRODUTOS=" + FAVALORPRODUTOS);
			sb.append(",FATIPOFATURA=" + FATIPOFATURA);
			sb.append(",FATOTALNFISCAL=" + FATOTALNFISCAL);
			sb.append(",FABASEICMS=" + FABASEICMS);
			sb.append(",FATOTALICM=" + FATOTALICM);
			sb.append(",FABASEREDUCAOIPI=" + FABASEREDUCAOIPI);
			sb.append(",FAVALORTOTALIPI=" + FAVALORTOTALIPI);
			sb.append(",FATOTALBASESUBST=" + FATOTALBASESUBST);
			sb.append(",FATOTALICMSUBST=" + FATOTALICMSUBST);
			sb.append(",FAINSCRICAOSUBSTITUTO=" + FAINSCRICAOSUBSTITUTO);
			sb.append(",FAVALORFRETE=" + FAVALORFRETE);
			sb.append(",FAVALORSEGURO=" + FAVALORSEGURO);
			sb.append(",FAVALOROUTRAS=" + FAVALOROUTRAS);
			sb.append(",FAVIATRANSPORTE=" + FAVIATRANSPORTE);
			sb.append(",FAMODALIDADEFRETE=" + FAMODALIDADEFRETE);
			sb.append(",FACODIGOTRANSPORTADOR=" + FACODIGOTRANSPORTADOR);
			sb.append(",FAQTDEVOLUMES=" + FAQTDEVOLUMES);
			sb.append(",FAESPECIEVOLUMES=" + FAESPECIEVOLUMES);
			sb.append(",FAMARCAVOLUMES=" + FAMARCAVOLUMES);
			sb.append(",FANUMERACAOVOLUMES=" + FANUMERACAOVOLUMES);
			sb.append(",FAPESOBRUTO=" + FAPESOBRUTO);
			sb.append(",FAPESOLIQUIDO=" + FAPESOLIQUIDO);
			sb.append(",FAIDENTIFICACAOVEICULO=" + FAIDENTIFICACAOVEICULO);
			sb.append(",FATIPONOTA=" + FATIPONOTA);
			sb.append(",ITNUMEROITEM=" + ITNUMEROITEM);
			sb.append(",ITCODIGOPRODUTO=" + ITCODIGOPRODUTO);
			sb.append(",ITCFOP=" + ITCFOP);
			sb.append(",ITCLASSIFICACAOFISCAL=" + ITCLASSIFICACAOFISCAL);
			sb.append(",ITSITTRIBUTARIAFEDERAL=" + ITSITTRIBUTARIAFEDERAL);
			sb.append(",ITQTDEPRODUTO=" + ITQTDEPRODUTO);
			sb.append(",ITUNIDADEMEDIDA=" + ITUNIDADEMEDIDA);
			sb.append(",ITVALORPRECOUNITARIO=" + ITVALORPRECOUNITARIO);
			sb.append(",ITVALORPRECOTOTAL=" + ITVALORPRECOTOTAL);
			sb.append(",ITVALORDESCONTO=" + ITVALORDESCONTO);
			sb.append(",ITDESPESASFRETE=" + ITDESPESASFRETE);
			sb.append(",ITDESPESASSEGURO=" + ITDESPESASSEGURO);
			sb.append(",ITOUTRASDESPESAS=" + ITOUTRASDESPESAS);
			sb.append(",ITINDMOVIMENTACAO=" + ITINDMOVIMENTACAO);
			sb.append(",ITVALORBASEICMS=" + ITVALORBASEICMS);
			sb.append(",ITALIQUOTAICMS=" + ITALIQUOTAICMS);
			sb.append(",ITVALORICMS=" + ITVALORICMS);
			sb.append(",ITVALORISENTOICM=" + ITVALORISENTOICM);
			sb.append(",ITVALOROUTROICM=" + ITVALOROUTROICM);
			sb.append(",ITVALORCONTABILICM=" + ITVALORCONTABILICM);
			sb.append(",FABASEPIS=" + FABASEPIS);
			sb.append(",ITBASEPIS=" + ITBASEPIS);
			sb.append(",ITALIQUOTAPIS=" + ITALIQUOTAPIS);
			sb.append(",ITVALORPIS=" + ITVALORPIS);
			sb.append(",FABASECOFINS=" + FABASECOFINS);
			sb.append(",ITBASECOFINS=" + ITBASECOFINS);
			sb.append(",ITALIQUOTACOFINS=" + ITALIQUOTACOFINS);
			sb.append(",ITVALORCOFINS=" + ITVALORCOFINS);
			sb.append(",ITDESCRCOMPLEMENTAR=" + ITDESCRCOMPLEMENTAR);
			sb.append(",ITPRECOTOTAL=" + ITPRECOTOTAL);
			sb.append(",ITSITUACAOTRIBUTARIAESTADUAL="
					+ ITSITUACAOTRIBUTARIAESTADUAL);
			sb.append(",ITSITTRIBCOFINS=" + ITSITTRIBCOFINS);
			sb.append(",ITSITTRIBPIS=" + ITSITTRIBPIS);
			sb.append(",ITCONTAANALITICA=" + ITCONTAANALITICA);
			sb.append(",FAINDMOVIMENTO=" + FAINDMOVIMENTO);
			sb.append(",ITUNIDADEPADRAO=" + ITUNIDADEPADRAO);
			sb.append(",ITINDTRIBUTACAOIPI=" + ITINDTRIBUTACAOIPI);
			sb.append(",ITVALORCONTABILIPI=" + ITVALORCONTABILIPI);
			sb.append(",ITVALORTOTALDOCUMENTO=" + ITVALORTOTALDOCUMENTO);
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
		final static byte[] commonByteArrayLock_client_conn_nfsproduto = new byte[0];
		static byte[] commonByteArray_client_conn_nfsproduto = new byte[0];

		public String FACODIGOEMPRESA;

		public String getFACODIGOEMPRESA() {
			return this.FACODIGOEMPRESA;
		}

		public String FAEMITENTE;

		public String getFAEMITENTE() {
			return this.FAEMITENTE;
		}

		public String FAMODELODOCUMENTO;

		public String getFAMODELODOCUMENTO() {
			return this.FAMODELODOCUMENTO;
		}

		public String FATIPODOCUMENTO;

		public String getFATIPODOCUMENTO() {
			return this.FATIPODOCUMENTO;
		}

		public String FASERIEDOCUMENTO;

		public String getFASERIEDOCUMENTO() {
			return this.FASERIEDOCUMENTO;
		}

		public String FANUMERODOCUMENTO;

		public String getFANUMERODOCUMENTO() {
			return this.FANUMERODOCUMENTO;
		}

		public String FANFECHAVE;

		public String getFANFECHAVE() {
			return this.FANFECHAVE;
		}

		public java.util.Date FADATAEMISSAO;

		public java.util.Date getFADATAEMISSAO() {
			return this.FADATAEMISSAO;
		}

		public java.util.Date FADATASAIDA;

		public java.util.Date getFADATASAIDA() {
			return this.FADATASAIDA;
		}

		public String FAHORASAIDA;

		public String getFAHORASAIDA() {
			return this.FAHORASAIDA;
		}

		public String FACODIGOCLIENTE;

		public String getFACODIGOCLIENTE() {
			return this.FACODIGOCLIENTE;
		}

		public String FAUFCLIENTE;

		public String getFAUFCLIENTE() {
			return this.FAUFCLIENTE;
		}

		public String FASITUACAODOC;

		public String getFASITUACAODOC() {
			return this.FASITUACAODOC;
		}

		public String FAINDCANCELAMENTO;

		public String getFAINDCANCELAMENTO() {
			return this.FAINDCANCELAMENTO;
		}

		public String FACONSUMIDOR;

		public String getFACONSUMIDOR() {
			return this.FACONSUMIDOR;
		}

		public String FACONTRIBUINTE;

		public String getFACONTRIBUINTE() {
			return this.FACONTRIBUINTE;
		}

		public String FAVALORPRODUTOS;

		public String getFAVALORPRODUTOS() {
			return this.FAVALORPRODUTOS;
		}

		public String FATIPOFATURA;

		public String getFATIPOFATURA() {
			return this.FATIPOFATURA;
		}

		public String FATOTALNFISCAL;

		public String getFATOTALNFISCAL() {
			return this.FATOTALNFISCAL;
		}

		public String FABASEICMS;

		public String getFABASEICMS() {
			return this.FABASEICMS;
		}

		public String FATOTALICM;

		public String getFATOTALICM() {
			return this.FATOTALICM;
		}

		public String FABASEREDUCAOIPI;

		public String getFABASEREDUCAOIPI() {
			return this.FABASEREDUCAOIPI;
		}

		public String FAVALORTOTALIPI;

		public String getFAVALORTOTALIPI() {
			return this.FAVALORTOTALIPI;
		}

		public String FATOTALBASESUBST;

		public String getFATOTALBASESUBST() {
			return this.FATOTALBASESUBST;
		}

		public String FATOTALICMSUBST;

		public String getFATOTALICMSUBST() {
			return this.FATOTALICMSUBST;
		}

		public String FAINSCRICAOSUBSTITUTO;

		public String getFAINSCRICAOSUBSTITUTO() {
			return this.FAINSCRICAOSUBSTITUTO;
		}

		public String FAVALORFRETE;

		public String getFAVALORFRETE() {
			return this.FAVALORFRETE;
		}

		public String FAVALORSEGURO;

		public String getFAVALORSEGURO() {
			return this.FAVALORSEGURO;
		}

		public String FAVALOROUTRAS;

		public String getFAVALOROUTRAS() {
			return this.FAVALOROUTRAS;
		}

		public String FAVIATRANSPORTE;

		public String getFAVIATRANSPORTE() {
			return this.FAVIATRANSPORTE;
		}

		public String FAMODALIDADEFRETE;

		public String getFAMODALIDADEFRETE() {
			return this.FAMODALIDADEFRETE;
		}

		public String FACODIGOTRANSPORTADOR;

		public String getFACODIGOTRANSPORTADOR() {
			return this.FACODIGOTRANSPORTADOR;
		}

		public String FAQTDEVOLUMES;

		public String getFAQTDEVOLUMES() {
			return this.FAQTDEVOLUMES;
		}

		public String FAESPECIEVOLUMES;

		public String getFAESPECIEVOLUMES() {
			return this.FAESPECIEVOLUMES;
		}

		public String FAMARCAVOLUMES;

		public String getFAMARCAVOLUMES() {
			return this.FAMARCAVOLUMES;
		}

		public String FANUMERACAOVOLUMES;

		public String getFANUMERACAOVOLUMES() {
			return this.FANUMERACAOVOLUMES;
		}

		public String FAPESOBRUTO;

		public String getFAPESOBRUTO() {
			return this.FAPESOBRUTO;
		}

		public String FAPESOLIQUIDO;

		public String getFAPESOLIQUIDO() {
			return this.FAPESOLIQUIDO;
		}

		public String FAIDENTIFICACAOVEICULO;

		public String getFAIDENTIFICACAOVEICULO() {
			return this.FAIDENTIFICACAOVEICULO;
		}

		public String FATIPONOTA;

		public String getFATIPONOTA() {
			return this.FATIPONOTA;
		}

		public String ITNUMEROITEM;

		public String getITNUMEROITEM() {
			return this.ITNUMEROITEM;
		}

		public String ITCODIGOPRODUTO;

		public String getITCODIGOPRODUTO() {
			return this.ITCODIGOPRODUTO;
		}

		public String ITCFOP;

		public String getITCFOP() {
			return this.ITCFOP;
		}

		public String ITCLASSIFICACAOFISCAL;

		public String getITCLASSIFICACAOFISCAL() {
			return this.ITCLASSIFICACAOFISCAL;
		}

		public String ITSITTRIBUTARIAFEDERAL;

		public String getITSITTRIBUTARIAFEDERAL() {
			return this.ITSITTRIBUTARIAFEDERAL;
		}

		public String ITQTDEPRODUTO;

		public String getITQTDEPRODUTO() {
			return this.ITQTDEPRODUTO;
		}

		public String ITUNIDADEMEDIDA;

		public String getITUNIDADEMEDIDA() {
			return this.ITUNIDADEMEDIDA;
		}

		public String ITVALORPRECOUNITARIO;

		public String getITVALORPRECOUNITARIO() {
			return this.ITVALORPRECOUNITARIO;
		}

		public String ITVALORPRECOTOTAL;

		public String getITVALORPRECOTOTAL() {
			return this.ITVALORPRECOTOTAL;
		}

		public String ITVALORDESCONTO;

		public String getITVALORDESCONTO() {
			return this.ITVALORDESCONTO;
		}

		public String ITDESPESASFRETE;

		public String getITDESPESASFRETE() {
			return this.ITDESPESASFRETE;
		}

		public String ITDESPESASSEGURO;

		public String getITDESPESASSEGURO() {
			return this.ITDESPESASSEGURO;
		}

		public String ITOUTRASDESPESAS;

		public String getITOUTRASDESPESAS() {
			return this.ITOUTRASDESPESAS;
		}

		public String ITINDMOVIMENTACAO;

		public String getITINDMOVIMENTACAO() {
			return this.ITINDMOVIMENTACAO;
		}

		public String ITVALORBASEICMS;

		public String getITVALORBASEICMS() {
			return this.ITVALORBASEICMS;
		}

		public String ITALIQUOTAICMS;

		public String getITALIQUOTAICMS() {
			return this.ITALIQUOTAICMS;
		}

		public String ITVALORICMS;

		public String getITVALORICMS() {
			return this.ITVALORICMS;
		}

		public String ITVALORISENTOICM;

		public String getITVALORISENTOICM() {
			return this.ITVALORISENTOICM;
		}

		public String ITVALOROUTROICM;

		public String getITVALOROUTROICM() {
			return this.ITVALOROUTROICM;
		}

		public String ITVALORCONTABILICM;

		public String getITVALORCONTABILICM() {
			return this.ITVALORCONTABILICM;
		}

		public String FABASEPIS;

		public String getFABASEPIS() {
			return this.FABASEPIS;
		}

		public String ITBASEPIS;

		public String getITBASEPIS() {
			return this.ITBASEPIS;
		}

		public String ITALIQUOTAPIS;

		public String getITALIQUOTAPIS() {
			return this.ITALIQUOTAPIS;
		}

		public String ITVALORPIS;

		public String getITVALORPIS() {
			return this.ITVALORPIS;
		}

		public String FABASECOFINS;

		public String getFABASECOFINS() {
			return this.FABASECOFINS;
		}

		public String ITBASECOFINS;

		public String getITBASECOFINS() {
			return this.ITBASECOFINS;
		}

		public String ITALIQUOTACOFINS;

		public String getITALIQUOTACOFINS() {
			return this.ITALIQUOTACOFINS;
		}

		public String ITVALORCOFINS;

		public String getITVALORCOFINS() {
			return this.ITVALORCOFINS;
		}

		public String ITDESCRCOMPLEMENTAR;

		public String getITDESCRCOMPLEMENTAR() {
			return this.ITDESCRCOMPLEMENTAR;
		}

		public String ITPRECOTOTAL;

		public String getITPRECOTOTAL() {
			return this.ITPRECOTOTAL;
		}

		public String ITSITUACAOTRIBUTARIAESTADUAL;

		public String getITSITUACAOTRIBUTARIAESTADUAL() {
			return this.ITSITUACAOTRIBUTARIAESTADUAL;
		}

		public String ITSITTRIBCOFINS;

		public String getITSITTRIBCOFINS() {
			return this.ITSITTRIBCOFINS;
		}

		public String ITSITTRIBPIS;

		public String getITSITTRIBPIS() {
			return this.ITSITTRIBPIS;
		}

		public String ITCONTAANALITICA;

		public String getITCONTAANALITICA() {
			return this.ITCONTAANALITICA;
		}

		public String FAINDMOVIMENTO;

		public String getFAINDMOVIMENTO() {
			return this.FAINDMOVIMENTO;
		}

		public String ITUNIDADEPADRAO;

		public String getITUNIDADEPADRAO() {
			return this.ITUNIDADEPADRAO;
		}

		public String ITINDTRIBUTACAOIPI;

		public String getITINDTRIBUTACAOIPI() {
			return this.ITINDTRIBUTACAOIPI;
		}

		public String ITVALORCONTABILIPI;

		public String getITVALORCONTABILIPI() {
			return this.ITVALORCONTABILIPI;
		}

		public String ITVALORTOTALDOCUMENTO;

		public String getITVALORTOTALDOCUMENTO() {
			return this.ITVALORTOTALDOCUMENTO;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_client_conn_nfsproduto.length) {
					if (length < 1024
							&& commonByteArray_client_conn_nfsproduto.length == 0) {
						commonByteArray_client_conn_nfsproduto = new byte[1024];
					} else {
						commonByteArray_client_conn_nfsproduto = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_client_conn_nfsproduto, 0,
						length);
				strReturn = new String(
						commonByteArray_client_conn_nfsproduto, 0, length,
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

			synchronized (commonByteArrayLock_client_conn_nfsproduto) {

				try {

					int length = 0;

					this.FACODIGOEMPRESA = readString(dis);

					this.FAEMITENTE = readString(dis);

					this.FAMODELODOCUMENTO = readString(dis);

					this.FATIPODOCUMENTO = readString(dis);

					this.FASERIEDOCUMENTO = readString(dis);

					this.FANUMERODOCUMENTO = readString(dis);

					this.FANFECHAVE = readString(dis);

					this.FADATAEMISSAO = readDate(dis);

					this.FADATASAIDA = readDate(dis);

					this.FAHORASAIDA = readString(dis);

					this.FACODIGOCLIENTE = readString(dis);

					this.FAUFCLIENTE = readString(dis);

					this.FASITUACAODOC = readString(dis);

					this.FAINDCANCELAMENTO = readString(dis);

					this.FACONSUMIDOR = readString(dis);

					this.FACONTRIBUINTE = readString(dis);

					this.FAVALORPRODUTOS = readString(dis);

					this.FATIPOFATURA = readString(dis);

					this.FATOTALNFISCAL = readString(dis);

					this.FABASEICMS = readString(dis);

					this.FATOTALICM = readString(dis);

					this.FABASEREDUCAOIPI = readString(dis);

					this.FAVALORTOTALIPI = readString(dis);

					this.FATOTALBASESUBST = readString(dis);

					this.FATOTALICMSUBST = readString(dis);

					this.FAINSCRICAOSUBSTITUTO = readString(dis);

					this.FAVALORFRETE = readString(dis);

					this.FAVALORSEGURO = readString(dis);

					this.FAVALOROUTRAS = readString(dis);

					this.FAVIATRANSPORTE = readString(dis);

					this.FAMODALIDADEFRETE = readString(dis);

					this.FACODIGOTRANSPORTADOR = readString(dis);

					this.FAQTDEVOLUMES = readString(dis);

					this.FAESPECIEVOLUMES = readString(dis);

					this.FAMARCAVOLUMES = readString(dis);

					this.FANUMERACAOVOLUMES = readString(dis);

					this.FAPESOBRUTO = readString(dis);

					this.FAPESOLIQUIDO = readString(dis);

					this.FAIDENTIFICACAOVEICULO = readString(dis);

					this.FATIPONOTA = readString(dis);

					this.ITNUMEROITEM = readString(dis);

					this.ITCODIGOPRODUTO = readString(dis);

					this.ITCFOP = readString(dis);

					this.ITCLASSIFICACAOFISCAL = readString(dis);

					this.ITSITTRIBUTARIAFEDERAL = readString(dis);

					this.ITQTDEPRODUTO = readString(dis);

					this.ITUNIDADEMEDIDA = readString(dis);

					this.ITVALORPRECOUNITARIO = readString(dis);

					this.ITVALORPRECOTOTAL = readString(dis);

					this.ITVALORDESCONTO = readString(dis);

					this.ITDESPESASFRETE = readString(dis);

					this.ITDESPESASSEGURO = readString(dis);

					this.ITOUTRASDESPESAS = readString(dis);

					this.ITINDMOVIMENTACAO = readString(dis);

					this.ITVALORBASEICMS = readString(dis);

					this.ITALIQUOTAICMS = readString(dis);

					this.ITVALORICMS = readString(dis);

					this.ITVALORISENTOICM = readString(dis);

					this.ITVALOROUTROICM = readString(dis);

					this.ITVALORCONTABILICM = readString(dis);

					this.FABASEPIS = readString(dis);

					this.ITBASEPIS = readString(dis);

					this.ITALIQUOTAPIS = readString(dis);

					this.ITVALORPIS = readString(dis);

					this.FABASECOFINS = readString(dis);

					this.ITBASECOFINS = readString(dis);

					this.ITALIQUOTACOFINS = readString(dis);

					this.ITVALORCOFINS = readString(dis);

					this.ITDESCRCOMPLEMENTAR = readString(dis);

					this.ITPRECOTOTAL = readString(dis);

					this.ITSITUACAOTRIBUTARIAESTADUAL = readString(dis);

					this.ITSITTRIBCOFINS = readString(dis);

					this.ITSITTRIBPIS = readString(dis);

					this.ITCONTAANALITICA = readString(dis);

					this.FAINDMOVIMENTO = readString(dis);

					this.ITUNIDADEPADRAO = readString(dis);

					this.ITINDTRIBUTACAOIPI = readString(dis);

					this.ITVALORCONTABILIPI = readString(dis);

					this.ITVALORTOTALDOCUMENTO = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.FACODIGOEMPRESA, dos);

				// String

				writeString(this.FAEMITENTE, dos);

				// String

				writeString(this.FAMODELODOCUMENTO, dos);

				// String

				writeString(this.FATIPODOCUMENTO, dos);

				// String

				writeString(this.FASERIEDOCUMENTO, dos);

				// String

				writeString(this.FANUMERODOCUMENTO, dos);

				// String

				writeString(this.FANFECHAVE, dos);

				// java.util.Date

				writeDate(this.FADATAEMISSAO, dos);

				// java.util.Date

				writeDate(this.FADATASAIDA, dos);

				// String

				writeString(this.FAHORASAIDA, dos);

				// String

				writeString(this.FACODIGOCLIENTE, dos);

				// String

				writeString(this.FAUFCLIENTE, dos);

				// String

				writeString(this.FASITUACAODOC, dos);

				// String

				writeString(this.FAINDCANCELAMENTO, dos);

				// String

				writeString(this.FACONSUMIDOR, dos);

				// String

				writeString(this.FACONTRIBUINTE, dos);

				// String

				writeString(this.FAVALORPRODUTOS, dos);

				// String

				writeString(this.FATIPOFATURA, dos);

				// String

				writeString(this.FATOTALNFISCAL, dos);

				// String

				writeString(this.FABASEICMS, dos);

				// String

				writeString(this.FATOTALICM, dos);

				// String

				writeString(this.FABASEREDUCAOIPI, dos);

				// String

				writeString(this.FAVALORTOTALIPI, dos);

				// String

				writeString(this.FATOTALBASESUBST, dos);

				// String

				writeString(this.FATOTALICMSUBST, dos);

				// String

				writeString(this.FAINSCRICAOSUBSTITUTO, dos);

				// String

				writeString(this.FAVALORFRETE, dos);

				// String

				writeString(this.FAVALORSEGURO, dos);

				// String

				writeString(this.FAVALOROUTRAS, dos);

				// String

				writeString(this.FAVIATRANSPORTE, dos);

				// String

				writeString(this.FAMODALIDADEFRETE, dos);

				// String

				writeString(this.FACODIGOTRANSPORTADOR, dos);

				// String

				writeString(this.FAQTDEVOLUMES, dos);

				// String

				writeString(this.FAESPECIEVOLUMES, dos);

				// String

				writeString(this.FAMARCAVOLUMES, dos);

				// String

				writeString(this.FANUMERACAOVOLUMES, dos);

				// String

				writeString(this.FAPESOBRUTO, dos);

				// String

				writeString(this.FAPESOLIQUIDO, dos);

				// String

				writeString(this.FAIDENTIFICACAOVEICULO, dos);

				// String

				writeString(this.FATIPONOTA, dos);

				// String

				writeString(this.ITNUMEROITEM, dos);

				// String

				writeString(this.ITCODIGOPRODUTO, dos);

				// String

				writeString(this.ITCFOP, dos);

				// String

				writeString(this.ITCLASSIFICACAOFISCAL, dos);

				// String

				writeString(this.ITSITTRIBUTARIAFEDERAL, dos);

				// String

				writeString(this.ITQTDEPRODUTO, dos);

				// String

				writeString(this.ITUNIDADEMEDIDA, dos);

				// String

				writeString(this.ITVALORPRECOUNITARIO, dos);

				// String

				writeString(this.ITVALORPRECOTOTAL, dos);

				// String

				writeString(this.ITVALORDESCONTO, dos);

				// String

				writeString(this.ITDESPESASFRETE, dos);

				// String

				writeString(this.ITDESPESASSEGURO, dos);

				// String

				writeString(this.ITOUTRASDESPESAS, dos);

				// String

				writeString(this.ITINDMOVIMENTACAO, dos);

				// String

				writeString(this.ITVALORBASEICMS, dos);

				// String

				writeString(this.ITALIQUOTAICMS, dos);

				// String

				writeString(this.ITVALORICMS, dos);

				// String

				writeString(this.ITVALORISENTOICM, dos);

				// String

				writeString(this.ITVALOROUTROICM, dos);

				// String

				writeString(this.ITVALORCONTABILICM, dos);

				// String

				writeString(this.FABASEPIS, dos);

				// String

				writeString(this.ITBASEPIS, dos);

				// String

				writeString(this.ITALIQUOTAPIS, dos);

				// String

				writeString(this.ITVALORPIS, dos);

				// String

				writeString(this.FABASECOFINS, dos);

				// String

				writeString(this.ITBASECOFINS, dos);

				// String

				writeString(this.ITALIQUOTACOFINS, dos);

				// String

				writeString(this.ITVALORCOFINS, dos);

				// String

				writeString(this.ITDESCRCOMPLEMENTAR, dos);

				// String

				writeString(this.ITPRECOTOTAL, dos);

				// String

				writeString(this.ITSITUACAOTRIBUTARIAESTADUAL, dos);

				// String

				writeString(this.ITSITTRIBCOFINS, dos);

				// String

				writeString(this.ITSITTRIBPIS, dos);

				// String

				writeString(this.ITCONTAANALITICA, dos);

				// String

				writeString(this.FAINDMOVIMENTO, dos);

				// String

				writeString(this.ITUNIDADEPADRAO, dos);

				// String

				writeString(this.ITINDTRIBUTACAOIPI, dos);

				// String

				writeString(this.ITVALORCONTABILIPI, dos);

				// String

				writeString(this.ITVALORTOTALDOCUMENTO, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("FACODIGOEMPRESA=" + FACODIGOEMPRESA);
			sb.append(",FAEMITENTE=" + FAEMITENTE);
			sb.append(",FAMODELODOCUMENTO=" + FAMODELODOCUMENTO);
			sb.append(",FATIPODOCUMENTO=" + FATIPODOCUMENTO);
			sb.append(",FASERIEDOCUMENTO=" + FASERIEDOCUMENTO);
			sb.append(",FANUMERODOCUMENTO=" + FANUMERODOCUMENTO);
			sb.append(",FANFECHAVE=" + FANFECHAVE);
			sb.append(",FADATAEMISSAO=" + String.valueOf(FADATAEMISSAO));
			sb.append(",FADATASAIDA=" + String.valueOf(FADATASAIDA));
			sb.append(",FAHORASAIDA=" + FAHORASAIDA);
			sb.append(",FACODIGOCLIENTE=" + FACODIGOCLIENTE);
			sb.append(",FAUFCLIENTE=" + FAUFCLIENTE);
			sb.append(",FASITUACAODOC=" + FASITUACAODOC);
			sb.append(",FAINDCANCELAMENTO=" + FAINDCANCELAMENTO);
			sb.append(",FACONSUMIDOR=" + FACONSUMIDOR);
			sb.append(",FACONTRIBUINTE=" + FACONTRIBUINTE);
			sb.append(",FAVALORPRODUTOS=" + FAVALORPRODUTOS);
			sb.append(",FATIPOFATURA=" + FATIPOFATURA);
			sb.append(",FATOTALNFISCAL=" + FATOTALNFISCAL);
			sb.append(",FABASEICMS=" + FABASEICMS);
			sb.append(",FATOTALICM=" + FATOTALICM);
			sb.append(",FABASEREDUCAOIPI=" + FABASEREDUCAOIPI);
			sb.append(",FAVALORTOTALIPI=" + FAVALORTOTALIPI);
			sb.append(",FATOTALBASESUBST=" + FATOTALBASESUBST);
			sb.append(",FATOTALICMSUBST=" + FATOTALICMSUBST);
			sb.append(",FAINSCRICAOSUBSTITUTO=" + FAINSCRICAOSUBSTITUTO);
			sb.append(",FAVALORFRETE=" + FAVALORFRETE);
			sb.append(",FAVALORSEGURO=" + FAVALORSEGURO);
			sb.append(",FAVALOROUTRAS=" + FAVALOROUTRAS);
			sb.append(",FAVIATRANSPORTE=" + FAVIATRANSPORTE);
			sb.append(",FAMODALIDADEFRETE=" + FAMODALIDADEFRETE);
			sb.append(",FACODIGOTRANSPORTADOR=" + FACODIGOTRANSPORTADOR);
			sb.append(",FAQTDEVOLUMES=" + FAQTDEVOLUMES);
			sb.append(",FAESPECIEVOLUMES=" + FAESPECIEVOLUMES);
			sb.append(",FAMARCAVOLUMES=" + FAMARCAVOLUMES);
			sb.append(",FANUMERACAOVOLUMES=" + FANUMERACAOVOLUMES);
			sb.append(",FAPESOBRUTO=" + FAPESOBRUTO);
			sb.append(",FAPESOLIQUIDO=" + FAPESOLIQUIDO);
			sb.append(",FAIDENTIFICACAOVEICULO=" + FAIDENTIFICACAOVEICULO);
			sb.append(",FATIPONOTA=" + FATIPONOTA);
			sb.append(",ITNUMEROITEM=" + ITNUMEROITEM);
			sb.append(",ITCODIGOPRODUTO=" + ITCODIGOPRODUTO);
			sb.append(",ITCFOP=" + ITCFOP);
			sb.append(",ITCLASSIFICACAOFISCAL=" + ITCLASSIFICACAOFISCAL);
			sb.append(",ITSITTRIBUTARIAFEDERAL=" + ITSITTRIBUTARIAFEDERAL);
			sb.append(",ITQTDEPRODUTO=" + ITQTDEPRODUTO);
			sb.append(",ITUNIDADEMEDIDA=" + ITUNIDADEMEDIDA);
			sb.append(",ITVALORPRECOUNITARIO=" + ITVALORPRECOUNITARIO);
			sb.append(",ITVALORPRECOTOTAL=" + ITVALORPRECOTOTAL);
			sb.append(",ITVALORDESCONTO=" + ITVALORDESCONTO);
			sb.append(",ITDESPESASFRETE=" + ITDESPESASFRETE);
			sb.append(",ITDESPESASSEGURO=" + ITDESPESASSEGURO);
			sb.append(",ITOUTRASDESPESAS=" + ITOUTRASDESPESAS);
			sb.append(",ITINDMOVIMENTACAO=" + ITINDMOVIMENTACAO);
			sb.append(",ITVALORBASEICMS=" + ITVALORBASEICMS);
			sb.append(",ITALIQUOTAICMS=" + ITALIQUOTAICMS);
			sb.append(",ITVALORICMS=" + ITVALORICMS);
			sb.append(",ITVALORISENTOICM=" + ITVALORISENTOICM);
			sb.append(",ITVALOROUTROICM=" + ITVALOROUTROICM);
			sb.append(",ITVALORCONTABILICM=" + ITVALORCONTABILICM);
			sb.append(",FABASEPIS=" + FABASEPIS);
			sb.append(",ITBASEPIS=" + ITBASEPIS);
			sb.append(",ITALIQUOTAPIS=" + ITALIQUOTAPIS);
			sb.append(",ITVALORPIS=" + ITVALORPIS);
			sb.append(",FABASECOFINS=" + FABASECOFINS);
			sb.append(",ITBASECOFINS=" + ITBASECOFINS);
			sb.append(",ITALIQUOTACOFINS=" + ITALIQUOTACOFINS);
			sb.append(",ITVALORCOFINS=" + ITVALORCOFINS);
			sb.append(",ITDESCRCOMPLEMENTAR=" + ITDESCRCOMPLEMENTAR);
			sb.append(",ITPRECOTOTAL=" + ITPRECOTOTAL);
			sb.append(",ITSITUACAOTRIBUTARIAESTADUAL="
					+ ITSITUACAOTRIBUTARIAESTADUAL);
			sb.append(",ITSITTRIBCOFINS=" + ITSITTRIBCOFINS);
			sb.append(",ITSITTRIBPIS=" + ITSITTRIBPIS);
			sb.append(",ITCONTAANALITICA=" + ITCONTAANALITICA);
			sb.append(",FAINDMOVIMENTO=" + FAINDMOVIMENTO);
			sb.append(",ITUNIDADEPADRAO=" + ITUNIDADEPADRAO);
			sb.append(",ITINDTRIBUTACAOIPI=" + ITINDTRIBUTACAOIPI);
			sb.append(",ITVALORCONTABILIPI=" + ITVALORCONTABILIPI);
			sb.append(",ITVALORTOTALDOCUMENTO=" + ITVALORTOTALDOCUMENTO);
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
		final static byte[] commonByteArrayLock_client_conn_nfsproduto = new byte[0];
		static byte[] commonByteArray_client_conn_nfsproduto = new byte[0];

		public String FACODIGOEMPRESA;

		public String getFACODIGOEMPRESA() {
			return this.FACODIGOEMPRESA;
		}

		public String FAEMITENTE;

		public String getFAEMITENTE() {
			return this.FAEMITENTE;
		}

		public String FAMODELODOCUMENTO;

		public String getFAMODELODOCUMENTO() {
			return this.FAMODELODOCUMENTO;
		}

		public String FATIPODOCUMENTO;

		public String getFATIPODOCUMENTO() {
			return this.FATIPODOCUMENTO;
		}

		public String FASERIEDOCUMENTO;

		public String getFASERIEDOCUMENTO() {
			return this.FASERIEDOCUMENTO;
		}

		public String FANUMERODOCUMENTO;

		public String getFANUMERODOCUMENTO() {
			return this.FANUMERODOCUMENTO;
		}

		public String FANFECHAVE;

		public String getFANFECHAVE() {
			return this.FANFECHAVE;
		}

		public java.util.Date FADATAEMISSAO;

		public java.util.Date getFADATAEMISSAO() {
			return this.FADATAEMISSAO;
		}

		public java.util.Date FADATASAIDA;

		public java.util.Date getFADATASAIDA() {
			return this.FADATASAIDA;
		}

		public String FAHORASAIDA;

		public String getFAHORASAIDA() {
			return this.FAHORASAIDA;
		}

		public String FACODIGOCLIENTE;

		public String getFACODIGOCLIENTE() {
			return this.FACODIGOCLIENTE;
		}

		public String FAUFCLIENTE;

		public String getFAUFCLIENTE() {
			return this.FAUFCLIENTE;
		}

		public String FASITUACAODOC;

		public String getFASITUACAODOC() {
			return this.FASITUACAODOC;
		}

		public String FAINDCANCELAMENTO;

		public String getFAINDCANCELAMENTO() {
			return this.FAINDCANCELAMENTO;
		}

		public String FACONSUMIDOR;

		public String getFACONSUMIDOR() {
			return this.FACONSUMIDOR;
		}

		public String FACONTRIBUINTE;

		public String getFACONTRIBUINTE() {
			return this.FACONTRIBUINTE;
		}

		public String FAVALORPRODUTOS;

		public String getFAVALORPRODUTOS() {
			return this.FAVALORPRODUTOS;
		}

		public String FATIPOFATURA;

		public String getFATIPOFATURA() {
			return this.FATIPOFATURA;
		}

		public String FATOTALNFISCAL;

		public String getFATOTALNFISCAL() {
			return this.FATOTALNFISCAL;
		}

		public String FABASEICMS;

		public String getFABASEICMS() {
			return this.FABASEICMS;
		}

		public String FATOTALICM;

		public String getFATOTALICM() {
			return this.FATOTALICM;
		}

		public String FABASEREDUCAOIPI;

		public String getFABASEREDUCAOIPI() {
			return this.FABASEREDUCAOIPI;
		}

		public String FAVALORTOTALIPI;

		public String getFAVALORTOTALIPI() {
			return this.FAVALORTOTALIPI;
		}

		public String FATOTALBASESUBST;

		public String getFATOTALBASESUBST() {
			return this.FATOTALBASESUBST;
		}

		public String FATOTALICMSUBST;

		public String getFATOTALICMSUBST() {
			return this.FATOTALICMSUBST;
		}

		public String FAINSCRICAOSUBSTITUTO;

		public String getFAINSCRICAOSUBSTITUTO() {
			return this.FAINSCRICAOSUBSTITUTO;
		}

		public String FAVALORFRETE;

		public String getFAVALORFRETE() {
			return this.FAVALORFRETE;
		}

		public String FAVALORSEGURO;

		public String getFAVALORSEGURO() {
			return this.FAVALORSEGURO;
		}

		public String FAVALOROUTRAS;

		public String getFAVALOROUTRAS() {
			return this.FAVALOROUTRAS;
		}

		public String FAVIATRANSPORTE;

		public String getFAVIATRANSPORTE() {
			return this.FAVIATRANSPORTE;
		}

		public String FAMODALIDADEFRETE;

		public String getFAMODALIDADEFRETE() {
			return this.FAMODALIDADEFRETE;
		}

		public String FACODIGOTRANSPORTADOR;

		public String getFACODIGOTRANSPORTADOR() {
			return this.FACODIGOTRANSPORTADOR;
		}

		public String FAQTDEVOLUMES;

		public String getFAQTDEVOLUMES() {
			return this.FAQTDEVOLUMES;
		}

		public String FAESPECIEVOLUMES;

		public String getFAESPECIEVOLUMES() {
			return this.FAESPECIEVOLUMES;
		}

		public String FAMARCAVOLUMES;

		public String getFAMARCAVOLUMES() {
			return this.FAMARCAVOLUMES;
		}

		public String FANUMERACAOVOLUMES;

		public String getFANUMERACAOVOLUMES() {
			return this.FANUMERACAOVOLUMES;
		}

		public String FAPESOBRUTO;

		public String getFAPESOBRUTO() {
			return this.FAPESOBRUTO;
		}

		public String FAPESOLIQUIDO;

		public String getFAPESOLIQUIDO() {
			return this.FAPESOLIQUIDO;
		}

		public String FAIDENTIFICACAOVEICULO;

		public String getFAIDENTIFICACAOVEICULO() {
			return this.FAIDENTIFICACAOVEICULO;
		}

		public String FATIPONOTA;

		public String getFATIPONOTA() {
			return this.FATIPONOTA;
		}

		public String ITNUMEROITEM;

		public String getITNUMEROITEM() {
			return this.ITNUMEROITEM;
		}

		public String ITCODIGOPRODUTO;

		public String getITCODIGOPRODUTO() {
			return this.ITCODIGOPRODUTO;
		}

		public String ITCFOP;

		public String getITCFOP() {
			return this.ITCFOP;
		}

		public String ITCLASSIFICACAOFISCAL;

		public String getITCLASSIFICACAOFISCAL() {
			return this.ITCLASSIFICACAOFISCAL;
		}

		public String ITSITTRIBUTARIAFEDERAL;

		public String getITSITTRIBUTARIAFEDERAL() {
			return this.ITSITTRIBUTARIAFEDERAL;
		}

		public String ITQTDEPRODUTO;

		public String getITQTDEPRODUTO() {
			return this.ITQTDEPRODUTO;
		}

		public String ITUNIDADEMEDIDA;

		public String getITUNIDADEMEDIDA() {
			return this.ITUNIDADEMEDIDA;
		}

		public String ITVALORPRECOUNITARIO;

		public String getITVALORPRECOUNITARIO() {
			return this.ITVALORPRECOUNITARIO;
		}

		public String ITVALORPRECOTOTAL;

		public String getITVALORPRECOTOTAL() {
			return this.ITVALORPRECOTOTAL;
		}

		public String ITVALORDESCONTO;

		public String getITVALORDESCONTO() {
			return this.ITVALORDESCONTO;
		}

		public String ITDESPESASFRETE;

		public String getITDESPESASFRETE() {
			return this.ITDESPESASFRETE;
		}

		public String ITDESPESASSEGURO;

		public String getITDESPESASSEGURO() {
			return this.ITDESPESASSEGURO;
		}

		public String ITOUTRASDESPESAS;

		public String getITOUTRASDESPESAS() {
			return this.ITOUTRASDESPESAS;
		}

		public String ITINDMOVIMENTACAO;

		public String getITINDMOVIMENTACAO() {
			return this.ITINDMOVIMENTACAO;
		}

		public String ITVALORBASEICMS;

		public String getITVALORBASEICMS() {
			return this.ITVALORBASEICMS;
		}

		public String ITALIQUOTAICMS;

		public String getITALIQUOTAICMS() {
			return this.ITALIQUOTAICMS;
		}

		public String ITVALORICMS;

		public String getITVALORICMS() {
			return this.ITVALORICMS;
		}

		public String ITVALORISENTOICM;

		public String getITVALORISENTOICM() {
			return this.ITVALORISENTOICM;
		}

		public String ITVALOROUTROICM;

		public String getITVALOROUTROICM() {
			return this.ITVALOROUTROICM;
		}

		public String ITVALORCONTABILICM;

		public String getITVALORCONTABILICM() {
			return this.ITVALORCONTABILICM;
		}

		public String FABASEPIS;

		public String getFABASEPIS() {
			return this.FABASEPIS;
		}

		public String ITBASEPIS;

		public String getITBASEPIS() {
			return this.ITBASEPIS;
		}

		public String ITALIQUOTAPIS;

		public String getITALIQUOTAPIS() {
			return this.ITALIQUOTAPIS;
		}

		public String ITVALORPIS;

		public String getITVALORPIS() {
			return this.ITVALORPIS;
		}

		public String FABASECOFINS;

		public String getFABASECOFINS() {
			return this.FABASECOFINS;
		}

		public String ITBASECOFINS;

		public String getITBASECOFINS() {
			return this.ITBASECOFINS;
		}

		public String ITALIQUOTACOFINS;

		public String getITALIQUOTACOFINS() {
			return this.ITALIQUOTACOFINS;
		}

		public String ITVALORCOFINS;

		public String getITVALORCOFINS() {
			return this.ITVALORCOFINS;
		}

		public String ITDESCRCOMPLEMENTAR;

		public String getITDESCRCOMPLEMENTAR() {
			return this.ITDESCRCOMPLEMENTAR;
		}

		public String ITPRECOTOTAL;

		public String getITPRECOTOTAL() {
			return this.ITPRECOTOTAL;
		}

		public String ITSITUACAOTRIBUTARIAESTADUAL;

		public String getITSITUACAOTRIBUTARIAESTADUAL() {
			return this.ITSITUACAOTRIBUTARIAESTADUAL;
		}

		public String ITSITTRIBCOFINS;

		public String getITSITTRIBCOFINS() {
			return this.ITSITTRIBCOFINS;
		}

		public String ITSITTRIBPIS;

		public String getITSITTRIBPIS() {
			return this.ITSITTRIBPIS;
		}

		public String ITCONTAANALITICA;

		public String getITCONTAANALITICA() {
			return this.ITCONTAANALITICA;
		}

		public String FAINDMOVIMENTO;

		public String getFAINDMOVIMENTO() {
			return this.FAINDMOVIMENTO;
		}

		public String ITUNIDADEPADRAO;

		public String getITUNIDADEPADRAO() {
			return this.ITUNIDADEPADRAO;
		}

		public String ITINDTRIBUTACAOIPI;

		public String getITINDTRIBUTACAOIPI() {
			return this.ITINDTRIBUTACAOIPI;
		}

		public String ITVALORCONTABILIPI;

		public String getITVALORCONTABILIPI() {
			return this.ITVALORCONTABILIPI;
		}

		public String ITVALORTOTALDOCUMENTO;

		public String getITVALORTOTALDOCUMENTO() {
			return this.ITVALORTOTALDOCUMENTO;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_client_conn_nfsproduto.length) {
					if (length < 1024
							&& commonByteArray_client_conn_nfsproduto.length == 0) {
						commonByteArray_client_conn_nfsproduto = new byte[1024];
					} else {
						commonByteArray_client_conn_nfsproduto = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_client_conn_nfsproduto, 0,
						length);
				strReturn = new String(
						commonByteArray_client_conn_nfsproduto, 0, length,
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

			synchronized (commonByteArrayLock_client_conn_nfsproduto) {

				try {

					int length = 0;

					this.FACODIGOEMPRESA = readString(dis);

					this.FAEMITENTE = readString(dis);

					this.FAMODELODOCUMENTO = readString(dis);

					this.FATIPODOCUMENTO = readString(dis);

					this.FASERIEDOCUMENTO = readString(dis);

					this.FANUMERODOCUMENTO = readString(dis);

					this.FANFECHAVE = readString(dis);

					this.FADATAEMISSAO = readDate(dis);

					this.FADATASAIDA = readDate(dis);

					this.FAHORASAIDA = readString(dis);

					this.FACODIGOCLIENTE = readString(dis);

					this.FAUFCLIENTE = readString(dis);

					this.FASITUACAODOC = readString(dis);

					this.FAINDCANCELAMENTO = readString(dis);

					this.FACONSUMIDOR = readString(dis);

					this.FACONTRIBUINTE = readString(dis);

					this.FAVALORPRODUTOS = readString(dis);

					this.FATIPOFATURA = readString(dis);

					this.FATOTALNFISCAL = readString(dis);

					this.FABASEICMS = readString(dis);

					this.FATOTALICM = readString(dis);

					this.FABASEREDUCAOIPI = readString(dis);

					this.FAVALORTOTALIPI = readString(dis);

					this.FATOTALBASESUBST = readString(dis);

					this.FATOTALICMSUBST = readString(dis);

					this.FAINSCRICAOSUBSTITUTO = readString(dis);

					this.FAVALORFRETE = readString(dis);

					this.FAVALORSEGURO = readString(dis);

					this.FAVALOROUTRAS = readString(dis);

					this.FAVIATRANSPORTE = readString(dis);

					this.FAMODALIDADEFRETE = readString(dis);

					this.FACODIGOTRANSPORTADOR = readString(dis);

					this.FAQTDEVOLUMES = readString(dis);

					this.FAESPECIEVOLUMES = readString(dis);

					this.FAMARCAVOLUMES = readString(dis);

					this.FANUMERACAOVOLUMES = readString(dis);

					this.FAPESOBRUTO = readString(dis);

					this.FAPESOLIQUIDO = readString(dis);

					this.FAIDENTIFICACAOVEICULO = readString(dis);

					this.FATIPONOTA = readString(dis);

					this.ITNUMEROITEM = readString(dis);

					this.ITCODIGOPRODUTO = readString(dis);

					this.ITCFOP = readString(dis);

					this.ITCLASSIFICACAOFISCAL = readString(dis);

					this.ITSITTRIBUTARIAFEDERAL = readString(dis);

					this.ITQTDEPRODUTO = readString(dis);

					this.ITUNIDADEMEDIDA = readString(dis);

					this.ITVALORPRECOUNITARIO = readString(dis);

					this.ITVALORPRECOTOTAL = readString(dis);

					this.ITVALORDESCONTO = readString(dis);

					this.ITDESPESASFRETE = readString(dis);

					this.ITDESPESASSEGURO = readString(dis);

					this.ITOUTRASDESPESAS = readString(dis);

					this.ITINDMOVIMENTACAO = readString(dis);

					this.ITVALORBASEICMS = readString(dis);

					this.ITALIQUOTAICMS = readString(dis);

					this.ITVALORICMS = readString(dis);

					this.ITVALORISENTOICM = readString(dis);

					this.ITVALOROUTROICM = readString(dis);

					this.ITVALORCONTABILICM = readString(dis);

					this.FABASEPIS = readString(dis);

					this.ITBASEPIS = readString(dis);

					this.ITALIQUOTAPIS = readString(dis);

					this.ITVALORPIS = readString(dis);

					this.FABASECOFINS = readString(dis);

					this.ITBASECOFINS = readString(dis);

					this.ITALIQUOTACOFINS = readString(dis);

					this.ITVALORCOFINS = readString(dis);

					this.ITDESCRCOMPLEMENTAR = readString(dis);

					this.ITPRECOTOTAL = readString(dis);

					this.ITSITUACAOTRIBUTARIAESTADUAL = readString(dis);

					this.ITSITTRIBCOFINS = readString(dis);

					this.ITSITTRIBPIS = readString(dis);

					this.ITCONTAANALITICA = readString(dis);

					this.FAINDMOVIMENTO = readString(dis);

					this.ITUNIDADEPADRAO = readString(dis);

					this.ITINDTRIBUTACAOIPI = readString(dis);

					this.ITVALORCONTABILIPI = readString(dis);

					this.ITVALORTOTALDOCUMENTO = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.FACODIGOEMPRESA, dos);

				// String

				writeString(this.FAEMITENTE, dos);

				// String

				writeString(this.FAMODELODOCUMENTO, dos);

				// String

				writeString(this.FATIPODOCUMENTO, dos);

				// String

				writeString(this.FASERIEDOCUMENTO, dos);

				// String

				writeString(this.FANUMERODOCUMENTO, dos);

				// String

				writeString(this.FANFECHAVE, dos);

				// java.util.Date

				writeDate(this.FADATAEMISSAO, dos);

				// java.util.Date

				writeDate(this.FADATASAIDA, dos);

				// String

				writeString(this.FAHORASAIDA, dos);

				// String

				writeString(this.FACODIGOCLIENTE, dos);

				// String

				writeString(this.FAUFCLIENTE, dos);

				// String

				writeString(this.FASITUACAODOC, dos);

				// String

				writeString(this.FAINDCANCELAMENTO, dos);

				// String

				writeString(this.FACONSUMIDOR, dos);

				// String

				writeString(this.FACONTRIBUINTE, dos);

				// String

				writeString(this.FAVALORPRODUTOS, dos);

				// String

				writeString(this.FATIPOFATURA, dos);

				// String

				writeString(this.FATOTALNFISCAL, dos);

				// String

				writeString(this.FABASEICMS, dos);

				// String

				writeString(this.FATOTALICM, dos);

				// String

				writeString(this.FABASEREDUCAOIPI, dos);

				// String

				writeString(this.FAVALORTOTALIPI, dos);

				// String

				writeString(this.FATOTALBASESUBST, dos);

				// String

				writeString(this.FATOTALICMSUBST, dos);

				// String

				writeString(this.FAINSCRICAOSUBSTITUTO, dos);

				// String

				writeString(this.FAVALORFRETE, dos);

				// String

				writeString(this.FAVALORSEGURO, dos);

				// String

				writeString(this.FAVALOROUTRAS, dos);

				// String

				writeString(this.FAVIATRANSPORTE, dos);

				// String

				writeString(this.FAMODALIDADEFRETE, dos);

				// String

				writeString(this.FACODIGOTRANSPORTADOR, dos);

				// String

				writeString(this.FAQTDEVOLUMES, dos);

				// String

				writeString(this.FAESPECIEVOLUMES, dos);

				// String

				writeString(this.FAMARCAVOLUMES, dos);

				// String

				writeString(this.FANUMERACAOVOLUMES, dos);

				// String

				writeString(this.FAPESOBRUTO, dos);

				// String

				writeString(this.FAPESOLIQUIDO, dos);

				// String

				writeString(this.FAIDENTIFICACAOVEICULO, dos);

				// String

				writeString(this.FATIPONOTA, dos);

				// String

				writeString(this.ITNUMEROITEM, dos);

				// String

				writeString(this.ITCODIGOPRODUTO, dos);

				// String

				writeString(this.ITCFOP, dos);

				// String

				writeString(this.ITCLASSIFICACAOFISCAL, dos);

				// String

				writeString(this.ITSITTRIBUTARIAFEDERAL, dos);

				// String

				writeString(this.ITQTDEPRODUTO, dos);

				// String

				writeString(this.ITUNIDADEMEDIDA, dos);

				// String

				writeString(this.ITVALORPRECOUNITARIO, dos);

				// String

				writeString(this.ITVALORPRECOTOTAL, dos);

				// String

				writeString(this.ITVALORDESCONTO, dos);

				// String

				writeString(this.ITDESPESASFRETE, dos);

				// String

				writeString(this.ITDESPESASSEGURO, dos);

				// String

				writeString(this.ITOUTRASDESPESAS, dos);

				// String

				writeString(this.ITINDMOVIMENTACAO, dos);

				// String

				writeString(this.ITVALORBASEICMS, dos);

				// String

				writeString(this.ITALIQUOTAICMS, dos);

				// String

				writeString(this.ITVALORICMS, dos);

				// String

				writeString(this.ITVALORISENTOICM, dos);

				// String

				writeString(this.ITVALOROUTROICM, dos);

				// String

				writeString(this.ITVALORCONTABILICM, dos);

				// String

				writeString(this.FABASEPIS, dos);

				// String

				writeString(this.ITBASEPIS, dos);

				// String

				writeString(this.ITALIQUOTAPIS, dos);

				// String

				writeString(this.ITVALORPIS, dos);

				// String

				writeString(this.FABASECOFINS, dos);

				// String

				writeString(this.ITBASECOFINS, dos);

				// String

				writeString(this.ITALIQUOTACOFINS, dos);

				// String

				writeString(this.ITVALORCOFINS, dos);

				// String

				writeString(this.ITDESCRCOMPLEMENTAR, dos);

				// String

				writeString(this.ITPRECOTOTAL, dos);

				// String

				writeString(this.ITSITUACAOTRIBUTARIAESTADUAL, dos);

				// String

				writeString(this.ITSITTRIBCOFINS, dos);

				// String

				writeString(this.ITSITTRIBPIS, dos);

				// String

				writeString(this.ITCONTAANALITICA, dos);

				// String

				writeString(this.FAINDMOVIMENTO, dos);

				// String

				writeString(this.ITUNIDADEPADRAO, dos);

				// String

				writeString(this.ITINDTRIBUTACAOIPI, dos);

				// String

				writeString(this.ITVALORCONTABILIPI, dos);

				// String

				writeString(this.ITVALORTOTALDOCUMENTO, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("FACODIGOEMPRESA=" + FACODIGOEMPRESA);
			sb.append(",FAEMITENTE=" + FAEMITENTE);
			sb.append(",FAMODELODOCUMENTO=" + FAMODELODOCUMENTO);
			sb.append(",FATIPODOCUMENTO=" + FATIPODOCUMENTO);
			sb.append(",FASERIEDOCUMENTO=" + FASERIEDOCUMENTO);
			sb.append(",FANUMERODOCUMENTO=" + FANUMERODOCUMENTO);
			sb.append(",FANFECHAVE=" + FANFECHAVE);
			sb.append(",FADATAEMISSAO=" + String.valueOf(FADATAEMISSAO));
			sb.append(",FADATASAIDA=" + String.valueOf(FADATASAIDA));
			sb.append(",FAHORASAIDA=" + FAHORASAIDA);
			sb.append(",FACODIGOCLIENTE=" + FACODIGOCLIENTE);
			sb.append(",FAUFCLIENTE=" + FAUFCLIENTE);
			sb.append(",FASITUACAODOC=" + FASITUACAODOC);
			sb.append(",FAINDCANCELAMENTO=" + FAINDCANCELAMENTO);
			sb.append(",FACONSUMIDOR=" + FACONSUMIDOR);
			sb.append(",FACONTRIBUINTE=" + FACONTRIBUINTE);
			sb.append(",FAVALORPRODUTOS=" + FAVALORPRODUTOS);
			sb.append(",FATIPOFATURA=" + FATIPOFATURA);
			sb.append(",FATOTALNFISCAL=" + FATOTALNFISCAL);
			sb.append(",FABASEICMS=" + FABASEICMS);
			sb.append(",FATOTALICM=" + FATOTALICM);
			sb.append(",FABASEREDUCAOIPI=" + FABASEREDUCAOIPI);
			sb.append(",FAVALORTOTALIPI=" + FAVALORTOTALIPI);
			sb.append(",FATOTALBASESUBST=" + FATOTALBASESUBST);
			sb.append(",FATOTALICMSUBST=" + FATOTALICMSUBST);
			sb.append(",FAINSCRICAOSUBSTITUTO=" + FAINSCRICAOSUBSTITUTO);
			sb.append(",FAVALORFRETE=" + FAVALORFRETE);
			sb.append(",FAVALORSEGURO=" + FAVALORSEGURO);
			sb.append(",FAVALOROUTRAS=" + FAVALOROUTRAS);
			sb.append(",FAVIATRANSPORTE=" + FAVIATRANSPORTE);
			sb.append(",FAMODALIDADEFRETE=" + FAMODALIDADEFRETE);
			sb.append(",FACODIGOTRANSPORTADOR=" + FACODIGOTRANSPORTADOR);
			sb.append(",FAQTDEVOLUMES=" + FAQTDEVOLUMES);
			sb.append(",FAESPECIEVOLUMES=" + FAESPECIEVOLUMES);
			sb.append(",FAMARCAVOLUMES=" + FAMARCAVOLUMES);
			sb.append(",FANUMERACAOVOLUMES=" + FANUMERACAOVOLUMES);
			sb.append(",FAPESOBRUTO=" + FAPESOBRUTO);
			sb.append(",FAPESOLIQUIDO=" + FAPESOLIQUIDO);
			sb.append(",FAIDENTIFICACAOVEICULO=" + FAIDENTIFICACAOVEICULO);
			sb.append(",FATIPONOTA=" + FATIPONOTA);
			sb.append(",ITNUMEROITEM=" + ITNUMEROITEM);
			sb.append(",ITCODIGOPRODUTO=" + ITCODIGOPRODUTO);
			sb.append(",ITCFOP=" + ITCFOP);
			sb.append(",ITCLASSIFICACAOFISCAL=" + ITCLASSIFICACAOFISCAL);
			sb.append(",ITSITTRIBUTARIAFEDERAL=" + ITSITTRIBUTARIAFEDERAL);
			sb.append(",ITQTDEPRODUTO=" + ITQTDEPRODUTO);
			sb.append(",ITUNIDADEMEDIDA=" + ITUNIDADEMEDIDA);
			sb.append(",ITVALORPRECOUNITARIO=" + ITVALORPRECOUNITARIO);
			sb.append(",ITVALORPRECOTOTAL=" + ITVALORPRECOTOTAL);
			sb.append(",ITVALORDESCONTO=" + ITVALORDESCONTO);
			sb.append(",ITDESPESASFRETE=" + ITDESPESASFRETE);
			sb.append(",ITDESPESASSEGURO=" + ITDESPESASSEGURO);
			sb.append(",ITOUTRASDESPESAS=" + ITOUTRASDESPESAS);
			sb.append(",ITINDMOVIMENTACAO=" + ITINDMOVIMENTACAO);
			sb.append(",ITVALORBASEICMS=" + ITVALORBASEICMS);
			sb.append(",ITALIQUOTAICMS=" + ITALIQUOTAICMS);
			sb.append(",ITVALORICMS=" + ITVALORICMS);
			sb.append(",ITVALORISENTOICM=" + ITVALORISENTOICM);
			sb.append(",ITVALOROUTROICM=" + ITVALOROUTROICM);
			sb.append(",ITVALORCONTABILICM=" + ITVALORCONTABILICM);
			sb.append(",FABASEPIS=" + FABASEPIS);
			sb.append(",ITBASEPIS=" + ITBASEPIS);
			sb.append(",ITALIQUOTAPIS=" + ITALIQUOTAPIS);
			sb.append(",ITVALORPIS=" + ITVALORPIS);
			sb.append(",FABASECOFINS=" + FABASECOFINS);
			sb.append(",ITBASECOFINS=" + ITBASECOFINS);
			sb.append(",ITALIQUOTACOFINS=" + ITALIQUOTACOFINS);
			sb.append(",ITVALORCOFINS=" + ITVALORCOFINS);
			sb.append(",ITDESCRCOMPLEMENTAR=" + ITDESCRCOMPLEMENTAR);
			sb.append(",ITPRECOTOTAL=" + ITPRECOTOTAL);
			sb.append(",ITSITUACAOTRIBUTARIAESTADUAL="
					+ ITSITUACAOTRIBUTARIAESTADUAL);
			sb.append(",ITSITTRIBCOFINS=" + ITSITTRIBCOFINS);
			sb.append(",ITSITTRIBPIS=" + ITSITTRIBPIS);
			sb.append(",ITCONTAANALITICA=" + ITCONTAANALITICA);
			sb.append(",FAINDMOVIMENTO=" + FAINDMOVIMENTO);
			sb.append(",ITUNIDADEPADRAO=" + ITUNIDADEPADRAO);
			sb.append(",ITINDTRIBUTACAOIPI=" + ITINDTRIBUTACAOIPI);
			sb.append(",ITVALORCONTABILIPI=" + ITVALORCONTABILIPI);
			sb.append(",ITVALORTOTALDOCUMENTO=" + ITVALORTOTALDOCUMENTO);
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

	public static class NFEPRODUTOStruct implements
			routines.system.IPersistableRow<NFEPRODUTOStruct> {
		final static byte[] commonByteArrayLock_client_conn_nfsproduto = new byte[0];
		static byte[] commonByteArray_client_conn_nfsproduto = new byte[0];

		public String FACODIGOEMPRESA;

		public String getFACODIGOEMPRESA() {
			return this.FACODIGOEMPRESA;
		}

		public String FAEMITENTE;

		public String getFAEMITENTE() {
			return this.FAEMITENTE;
		}

		public String FAMODELODOCUMENTO;

		public String getFAMODELODOCUMENTO() {
			return this.FAMODELODOCUMENTO;
		}

		public String FATIPODOCUMENTO;

		public String getFATIPODOCUMENTO() {
			return this.FATIPODOCUMENTO;
		}

		public String FASERIEDOCUMENTO;

		public String getFASERIEDOCUMENTO() {
			return this.FASERIEDOCUMENTO;
		}

		public String FANUMERODOCUMENTO;

		public String getFANUMERODOCUMENTO() {
			return this.FANUMERODOCUMENTO;
		}

		public String FANFECHAVE;

		public String getFANFECHAVE() {
			return this.FANFECHAVE;
		}

		public java.util.Date FADATAEMISSAO;

		public java.util.Date getFADATAEMISSAO() {
			return this.FADATAEMISSAO;
		}

		public java.util.Date FADATASAIDA;

		public java.util.Date getFADATASAIDA() {
			return this.FADATASAIDA;
		}

		public String FAHORASAIDA;

		public String getFAHORASAIDA() {
			return this.FAHORASAIDA;
		}

		public String FACODIGOCLIENTE;

		public String getFACODIGOCLIENTE() {
			return this.FACODIGOCLIENTE;
		}

		public String FAUFCLIENTE;

		public String getFAUFCLIENTE() {
			return this.FAUFCLIENTE;
		}

		public String FASITUACAODOC;

		public String getFASITUACAODOC() {
			return this.FASITUACAODOC;
		}

		public String FAINDCANCELAMENTO;

		public String getFAINDCANCELAMENTO() {
			return this.FAINDCANCELAMENTO;
		}

		public String FACONSUMIDOR;

		public String getFACONSUMIDOR() {
			return this.FACONSUMIDOR;
		}

		public String FACONTRIBUINTE;

		public String getFACONTRIBUINTE() {
			return this.FACONTRIBUINTE;
		}

		public String FAVALORPRODUTOS;

		public String getFAVALORPRODUTOS() {
			return this.FAVALORPRODUTOS;
		}

		public String FATIPOFATURA;

		public String getFATIPOFATURA() {
			return this.FATIPOFATURA;
		}

		public String FATOTALNFISCAL;

		public String getFATOTALNFISCAL() {
			return this.FATOTALNFISCAL;
		}

		public String FABASEICMS;

		public String getFABASEICMS() {
			return this.FABASEICMS;
		}

		public String FATOTALICM;

		public String getFATOTALICM() {
			return this.FATOTALICM;
		}

		public String FABASEREDUCAOIPI;

		public String getFABASEREDUCAOIPI() {
			return this.FABASEREDUCAOIPI;
		}

		public String FAVALORTOTALIPI;

		public String getFAVALORTOTALIPI() {
			return this.FAVALORTOTALIPI;
		}

		public String FATOTALBASESUBST;

		public String getFATOTALBASESUBST() {
			return this.FATOTALBASESUBST;
		}

		public String FATOTALICMSUBST;

		public String getFATOTALICMSUBST() {
			return this.FATOTALICMSUBST;
		}

		public String FAINSCRICAOSUBSTITUTO;

		public String getFAINSCRICAOSUBSTITUTO() {
			return this.FAINSCRICAOSUBSTITUTO;
		}

		public String FAVALORFRETE;

		public String getFAVALORFRETE() {
			return this.FAVALORFRETE;
		}

		public String FAVALORSEGURO;

		public String getFAVALORSEGURO() {
			return this.FAVALORSEGURO;
		}

		public String FAVALOROUTRAS;

		public String getFAVALOROUTRAS() {
			return this.FAVALOROUTRAS;
		}

		public String FAVIATRANSPORTE;

		public String getFAVIATRANSPORTE() {
			return this.FAVIATRANSPORTE;
		}

		public String FAMODALIDADEFRETE;

		public String getFAMODALIDADEFRETE() {
			return this.FAMODALIDADEFRETE;
		}

		public String FACODIGOTRANSPORTADOR;

		public String getFACODIGOTRANSPORTADOR() {
			return this.FACODIGOTRANSPORTADOR;
		}

		public String FAQTDEVOLUMES;

		public String getFAQTDEVOLUMES() {
			return this.FAQTDEVOLUMES;
		}

		public String FAESPECIEVOLUMES;

		public String getFAESPECIEVOLUMES() {
			return this.FAESPECIEVOLUMES;
		}

		public String FAMARCAVOLUMES;

		public String getFAMARCAVOLUMES() {
			return this.FAMARCAVOLUMES;
		}

		public String FANUMERACAOVOLUMES;

		public String getFANUMERACAOVOLUMES() {
			return this.FANUMERACAOVOLUMES;
		}

		public String FAPESOBRUTO;

		public String getFAPESOBRUTO() {
			return this.FAPESOBRUTO;
		}

		public String FAPESOLIQUIDO;

		public String getFAPESOLIQUIDO() {
			return this.FAPESOLIQUIDO;
		}

		public String FAIDENTIFICACAOVEICULO;

		public String getFAIDENTIFICACAOVEICULO() {
			return this.FAIDENTIFICACAOVEICULO;
		}

		public String FATIPONOTA;

		public String getFATIPONOTA() {
			return this.FATIPONOTA;
		}

		public String ITNUMEROITEM;

		public String getITNUMEROITEM() {
			return this.ITNUMEROITEM;
		}

		public String ITCODIGOPRODUTO;

		public String getITCODIGOPRODUTO() {
			return this.ITCODIGOPRODUTO;
		}

		public String ITCFOP;

		public String getITCFOP() {
			return this.ITCFOP;
		}

		public String ITCLASSIFICACAOFISCAL;

		public String getITCLASSIFICACAOFISCAL() {
			return this.ITCLASSIFICACAOFISCAL;
		}

		public String ITSITTRIBUTARIAFEDERAL;

		public String getITSITTRIBUTARIAFEDERAL() {
			return this.ITSITTRIBUTARIAFEDERAL;
		}

		public String ITQTDEPRODUTO;

		public String getITQTDEPRODUTO() {
			return this.ITQTDEPRODUTO;
		}

		public String ITUNIDADEMEDIDA;

		public String getITUNIDADEMEDIDA() {
			return this.ITUNIDADEMEDIDA;
		}

		public String ITVALORPRECOUNITARIO;

		public String getITVALORPRECOUNITARIO() {
			return this.ITVALORPRECOUNITARIO;
		}

		public String ITVALORPRECOTOTAL;

		public String getITVALORPRECOTOTAL() {
			return this.ITVALORPRECOTOTAL;
		}

		public String ITVALORDESCONTO;

		public String getITVALORDESCONTO() {
			return this.ITVALORDESCONTO;
		}

		public String ITDESPESASFRETE;

		public String getITDESPESASFRETE() {
			return this.ITDESPESASFRETE;
		}

		public String ITDESPESASSEGURO;

		public String getITDESPESASSEGURO() {
			return this.ITDESPESASSEGURO;
		}

		public String ITOUTRASDESPESAS;

		public String getITOUTRASDESPESAS() {
			return this.ITOUTRASDESPESAS;
		}

		public String ITINDMOVIMENTACAO;

		public String getITINDMOVIMENTACAO() {
			return this.ITINDMOVIMENTACAO;
		}

		public String ITVALORBASEICMS;

		public String getITVALORBASEICMS() {
			return this.ITVALORBASEICMS;
		}

		public String ITALIQUOTAICMS;

		public String getITALIQUOTAICMS() {
			return this.ITALIQUOTAICMS;
		}

		public String ITVALORICMS;

		public String getITVALORICMS() {
			return this.ITVALORICMS;
		}

		public String ITVALORISENTOICM;

		public String getITVALORISENTOICM() {
			return this.ITVALORISENTOICM;
		}

		public String ITVALOROUTROICM;

		public String getITVALOROUTROICM() {
			return this.ITVALOROUTROICM;
		}

		public String ITVALORCONTABILICM;

		public String getITVALORCONTABILICM() {
			return this.ITVALORCONTABILICM;
		}

		public String FABASEPIS;

		public String getFABASEPIS() {
			return this.FABASEPIS;
		}

		public String ITBASEPIS;

		public String getITBASEPIS() {
			return this.ITBASEPIS;
		}

		public String ITALIQUOTAPIS;

		public String getITALIQUOTAPIS() {
			return this.ITALIQUOTAPIS;
		}

		public String ITVALORPIS;

		public String getITVALORPIS() {
			return this.ITVALORPIS;
		}

		public String FABASECOFINS;

		public String getFABASECOFINS() {
			return this.FABASECOFINS;
		}

		public String ITBASECOFINS;

		public String getITBASECOFINS() {
			return this.ITBASECOFINS;
		}

		public String ITALIQUOTACOFINS;

		public String getITALIQUOTACOFINS() {
			return this.ITALIQUOTACOFINS;
		}

		public String ITVALORCOFINS;

		public String getITVALORCOFINS() {
			return this.ITVALORCOFINS;
		}

		public String ITDESCRCOMPLEMENTAR;

		public String getITDESCRCOMPLEMENTAR() {
			return this.ITDESCRCOMPLEMENTAR;
		}

		public String ITPRECOTOTAL;

		public String getITPRECOTOTAL() {
			return this.ITPRECOTOTAL;
		}

		public String ITSITUACAOTRIBUTARIAESTADUAL;

		public String getITSITUACAOTRIBUTARIAESTADUAL() {
			return this.ITSITUACAOTRIBUTARIAESTADUAL;
		}

		public String ITSITTRIBCOFINS;

		public String getITSITTRIBCOFINS() {
			return this.ITSITTRIBCOFINS;
		}

		public String ITSITTRIBPIS;

		public String getITSITTRIBPIS() {
			return this.ITSITTRIBPIS;
		}

		public String ITCONTAANALITICA;

		public String getITCONTAANALITICA() {
			return this.ITCONTAANALITICA;
		}

		public String FAINDMOVIMENTO;

		public String getFAINDMOVIMENTO() {
			return this.FAINDMOVIMENTO;
		}

		public String ITUNIDADEPADRAO;

		public String getITUNIDADEPADRAO() {
			return this.ITUNIDADEPADRAO;
		}

		public String ITINDTRIBUTACAOIPI;

		public String getITINDTRIBUTACAOIPI() {
			return this.ITINDTRIBUTACAOIPI;
		}

		public String ITVALORCONTABILIPI;

		public String getITVALORCONTABILIPI() {
			return this.ITVALORCONTABILIPI;
		}

		public String ITVALORTOTALDOCUMENTO;

		public String getITVALORTOTALDOCUMENTO() {
			return this.ITVALORTOTALDOCUMENTO;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_client_conn_nfsproduto.length) {
					if (length < 1024
							&& commonByteArray_client_conn_nfsproduto.length == 0) {
						commonByteArray_client_conn_nfsproduto = new byte[1024];
					} else {
						commonByteArray_client_conn_nfsproduto = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_client_conn_nfsproduto, 0,
						length);
				strReturn = new String(
						commonByteArray_client_conn_nfsproduto, 0, length,
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

			synchronized (commonByteArrayLock_client_conn_nfsproduto) {

				try {

					int length = 0;

					this.FACODIGOEMPRESA = readString(dis);

					this.FAEMITENTE = readString(dis);

					this.FAMODELODOCUMENTO = readString(dis);

					this.FATIPODOCUMENTO = readString(dis);

					this.FASERIEDOCUMENTO = readString(dis);

					this.FANUMERODOCUMENTO = readString(dis);

					this.FANFECHAVE = readString(dis);

					this.FADATAEMISSAO = readDate(dis);

					this.FADATASAIDA = readDate(dis);

					this.FAHORASAIDA = readString(dis);

					this.FACODIGOCLIENTE = readString(dis);

					this.FAUFCLIENTE = readString(dis);

					this.FASITUACAODOC = readString(dis);

					this.FAINDCANCELAMENTO = readString(dis);

					this.FACONSUMIDOR = readString(dis);

					this.FACONTRIBUINTE = readString(dis);

					this.FAVALORPRODUTOS = readString(dis);

					this.FATIPOFATURA = readString(dis);

					this.FATOTALNFISCAL = readString(dis);

					this.FABASEICMS = readString(dis);

					this.FATOTALICM = readString(dis);

					this.FABASEREDUCAOIPI = readString(dis);

					this.FAVALORTOTALIPI = readString(dis);

					this.FATOTALBASESUBST = readString(dis);

					this.FATOTALICMSUBST = readString(dis);

					this.FAINSCRICAOSUBSTITUTO = readString(dis);

					this.FAVALORFRETE = readString(dis);

					this.FAVALORSEGURO = readString(dis);

					this.FAVALOROUTRAS = readString(dis);

					this.FAVIATRANSPORTE = readString(dis);

					this.FAMODALIDADEFRETE = readString(dis);

					this.FACODIGOTRANSPORTADOR = readString(dis);

					this.FAQTDEVOLUMES = readString(dis);

					this.FAESPECIEVOLUMES = readString(dis);

					this.FAMARCAVOLUMES = readString(dis);

					this.FANUMERACAOVOLUMES = readString(dis);

					this.FAPESOBRUTO = readString(dis);

					this.FAPESOLIQUIDO = readString(dis);

					this.FAIDENTIFICACAOVEICULO = readString(dis);

					this.FATIPONOTA = readString(dis);

					this.ITNUMEROITEM = readString(dis);

					this.ITCODIGOPRODUTO = readString(dis);

					this.ITCFOP = readString(dis);

					this.ITCLASSIFICACAOFISCAL = readString(dis);

					this.ITSITTRIBUTARIAFEDERAL = readString(dis);

					this.ITQTDEPRODUTO = readString(dis);

					this.ITUNIDADEMEDIDA = readString(dis);

					this.ITVALORPRECOUNITARIO = readString(dis);

					this.ITVALORPRECOTOTAL = readString(dis);

					this.ITVALORDESCONTO = readString(dis);

					this.ITDESPESASFRETE = readString(dis);

					this.ITDESPESASSEGURO = readString(dis);

					this.ITOUTRASDESPESAS = readString(dis);

					this.ITINDMOVIMENTACAO = readString(dis);

					this.ITVALORBASEICMS = readString(dis);

					this.ITALIQUOTAICMS = readString(dis);

					this.ITVALORICMS = readString(dis);

					this.ITVALORISENTOICM = readString(dis);

					this.ITVALOROUTROICM = readString(dis);

					this.ITVALORCONTABILICM = readString(dis);

					this.FABASEPIS = readString(dis);

					this.ITBASEPIS = readString(dis);

					this.ITALIQUOTAPIS = readString(dis);

					this.ITVALORPIS = readString(dis);

					this.FABASECOFINS = readString(dis);

					this.ITBASECOFINS = readString(dis);

					this.ITALIQUOTACOFINS = readString(dis);

					this.ITVALORCOFINS = readString(dis);

					this.ITDESCRCOMPLEMENTAR = readString(dis);

					this.ITPRECOTOTAL = readString(dis);

					this.ITSITUACAOTRIBUTARIAESTADUAL = readString(dis);

					this.ITSITTRIBCOFINS = readString(dis);

					this.ITSITTRIBPIS = readString(dis);

					this.ITCONTAANALITICA = readString(dis);

					this.FAINDMOVIMENTO = readString(dis);

					this.ITUNIDADEPADRAO = readString(dis);

					this.ITINDTRIBUTACAOIPI = readString(dis);

					this.ITVALORCONTABILIPI = readString(dis);

					this.ITVALORTOTALDOCUMENTO = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.FACODIGOEMPRESA, dos);

				// String

				writeString(this.FAEMITENTE, dos);

				// String

				writeString(this.FAMODELODOCUMENTO, dos);

				// String

				writeString(this.FATIPODOCUMENTO, dos);

				// String

				writeString(this.FASERIEDOCUMENTO, dos);

				// String

				writeString(this.FANUMERODOCUMENTO, dos);

				// String

				writeString(this.FANFECHAVE, dos);

				// java.util.Date

				writeDate(this.FADATAEMISSAO, dos);

				// java.util.Date

				writeDate(this.FADATASAIDA, dos);

				// String

				writeString(this.FAHORASAIDA, dos);

				// String

				writeString(this.FACODIGOCLIENTE, dos);

				// String

				writeString(this.FAUFCLIENTE, dos);

				// String

				writeString(this.FASITUACAODOC, dos);

				// String

				writeString(this.FAINDCANCELAMENTO, dos);

				// String

				writeString(this.FACONSUMIDOR, dos);

				// String

				writeString(this.FACONTRIBUINTE, dos);

				// String

				writeString(this.FAVALORPRODUTOS, dos);

				// String

				writeString(this.FATIPOFATURA, dos);

				// String

				writeString(this.FATOTALNFISCAL, dos);

				// String

				writeString(this.FABASEICMS, dos);

				// String

				writeString(this.FATOTALICM, dos);

				// String

				writeString(this.FABASEREDUCAOIPI, dos);

				// String

				writeString(this.FAVALORTOTALIPI, dos);

				// String

				writeString(this.FATOTALBASESUBST, dos);

				// String

				writeString(this.FATOTALICMSUBST, dos);

				// String

				writeString(this.FAINSCRICAOSUBSTITUTO, dos);

				// String

				writeString(this.FAVALORFRETE, dos);

				// String

				writeString(this.FAVALORSEGURO, dos);

				// String

				writeString(this.FAVALOROUTRAS, dos);

				// String

				writeString(this.FAVIATRANSPORTE, dos);

				// String

				writeString(this.FAMODALIDADEFRETE, dos);

				// String

				writeString(this.FACODIGOTRANSPORTADOR, dos);

				// String

				writeString(this.FAQTDEVOLUMES, dos);

				// String

				writeString(this.FAESPECIEVOLUMES, dos);

				// String

				writeString(this.FAMARCAVOLUMES, dos);

				// String

				writeString(this.FANUMERACAOVOLUMES, dos);

				// String

				writeString(this.FAPESOBRUTO, dos);

				// String

				writeString(this.FAPESOLIQUIDO, dos);

				// String

				writeString(this.FAIDENTIFICACAOVEICULO, dos);

				// String

				writeString(this.FATIPONOTA, dos);

				// String

				writeString(this.ITNUMEROITEM, dos);

				// String

				writeString(this.ITCODIGOPRODUTO, dos);

				// String

				writeString(this.ITCFOP, dos);

				// String

				writeString(this.ITCLASSIFICACAOFISCAL, dos);

				// String

				writeString(this.ITSITTRIBUTARIAFEDERAL, dos);

				// String

				writeString(this.ITQTDEPRODUTO, dos);

				// String

				writeString(this.ITUNIDADEMEDIDA, dos);

				// String

				writeString(this.ITVALORPRECOUNITARIO, dos);

				// String

				writeString(this.ITVALORPRECOTOTAL, dos);

				// String

				writeString(this.ITVALORDESCONTO, dos);

				// String

				writeString(this.ITDESPESASFRETE, dos);

				// String

				writeString(this.ITDESPESASSEGURO, dos);

				// String

				writeString(this.ITOUTRASDESPESAS, dos);

				// String

				writeString(this.ITINDMOVIMENTACAO, dos);

				// String

				writeString(this.ITVALORBASEICMS, dos);

				// String

				writeString(this.ITALIQUOTAICMS, dos);

				// String

				writeString(this.ITVALORICMS, dos);

				// String

				writeString(this.ITVALORISENTOICM, dos);

				// String

				writeString(this.ITVALOROUTROICM, dos);

				// String

				writeString(this.ITVALORCONTABILICM, dos);

				// String

				writeString(this.FABASEPIS, dos);

				// String

				writeString(this.ITBASEPIS, dos);

				// String

				writeString(this.ITALIQUOTAPIS, dos);

				// String

				writeString(this.ITVALORPIS, dos);

				// String

				writeString(this.FABASECOFINS, dos);

				// String

				writeString(this.ITBASECOFINS, dos);

				// String

				writeString(this.ITALIQUOTACOFINS, dos);

				// String

				writeString(this.ITVALORCOFINS, dos);

				// String

				writeString(this.ITDESCRCOMPLEMENTAR, dos);

				// String

				writeString(this.ITPRECOTOTAL, dos);

				// String

				writeString(this.ITSITUACAOTRIBUTARIAESTADUAL, dos);

				// String

				writeString(this.ITSITTRIBCOFINS, dos);

				// String

				writeString(this.ITSITTRIBPIS, dos);

				// String

				writeString(this.ITCONTAANALITICA, dos);

				// String

				writeString(this.FAINDMOVIMENTO, dos);

				// String

				writeString(this.ITUNIDADEPADRAO, dos);

				// String

				writeString(this.ITINDTRIBUTACAOIPI, dos);

				// String

				writeString(this.ITVALORCONTABILIPI, dos);

				// String

				writeString(this.ITVALORTOTALDOCUMENTO, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("FACODIGOEMPRESA=" + FACODIGOEMPRESA);
			sb.append(",FAEMITENTE=" + FAEMITENTE);
			sb.append(",FAMODELODOCUMENTO=" + FAMODELODOCUMENTO);
			sb.append(",FATIPODOCUMENTO=" + FATIPODOCUMENTO);
			sb.append(",FASERIEDOCUMENTO=" + FASERIEDOCUMENTO);
			sb.append(",FANUMERODOCUMENTO=" + FANUMERODOCUMENTO);
			sb.append(",FANFECHAVE=" + FANFECHAVE);
			sb.append(",FADATAEMISSAO=" + String.valueOf(FADATAEMISSAO));
			sb.append(",FADATASAIDA=" + String.valueOf(FADATASAIDA));
			sb.append(",FAHORASAIDA=" + FAHORASAIDA);
			sb.append(",FACODIGOCLIENTE=" + FACODIGOCLIENTE);
			sb.append(",FAUFCLIENTE=" + FAUFCLIENTE);
			sb.append(",FASITUACAODOC=" + FASITUACAODOC);
			sb.append(",FAINDCANCELAMENTO=" + FAINDCANCELAMENTO);
			sb.append(",FACONSUMIDOR=" + FACONSUMIDOR);
			sb.append(",FACONTRIBUINTE=" + FACONTRIBUINTE);
			sb.append(",FAVALORPRODUTOS=" + FAVALORPRODUTOS);
			sb.append(",FATIPOFATURA=" + FATIPOFATURA);
			sb.append(",FATOTALNFISCAL=" + FATOTALNFISCAL);
			sb.append(",FABASEICMS=" + FABASEICMS);
			sb.append(",FATOTALICM=" + FATOTALICM);
			sb.append(",FABASEREDUCAOIPI=" + FABASEREDUCAOIPI);
			sb.append(",FAVALORTOTALIPI=" + FAVALORTOTALIPI);
			sb.append(",FATOTALBASESUBST=" + FATOTALBASESUBST);
			sb.append(",FATOTALICMSUBST=" + FATOTALICMSUBST);
			sb.append(",FAINSCRICAOSUBSTITUTO=" + FAINSCRICAOSUBSTITUTO);
			sb.append(",FAVALORFRETE=" + FAVALORFRETE);
			sb.append(",FAVALORSEGURO=" + FAVALORSEGURO);
			sb.append(",FAVALOROUTRAS=" + FAVALOROUTRAS);
			sb.append(",FAVIATRANSPORTE=" + FAVIATRANSPORTE);
			sb.append(",FAMODALIDADEFRETE=" + FAMODALIDADEFRETE);
			sb.append(",FACODIGOTRANSPORTADOR=" + FACODIGOTRANSPORTADOR);
			sb.append(",FAQTDEVOLUMES=" + FAQTDEVOLUMES);
			sb.append(",FAESPECIEVOLUMES=" + FAESPECIEVOLUMES);
			sb.append(",FAMARCAVOLUMES=" + FAMARCAVOLUMES);
			sb.append(",FANUMERACAOVOLUMES=" + FANUMERACAOVOLUMES);
			sb.append(",FAPESOBRUTO=" + FAPESOBRUTO);
			sb.append(",FAPESOLIQUIDO=" + FAPESOLIQUIDO);
			sb.append(",FAIDENTIFICACAOVEICULO=" + FAIDENTIFICACAOVEICULO);
			sb.append(",FATIPONOTA=" + FATIPONOTA);
			sb.append(",ITNUMEROITEM=" + ITNUMEROITEM);
			sb.append(",ITCODIGOPRODUTO=" + ITCODIGOPRODUTO);
			sb.append(",ITCFOP=" + ITCFOP);
			sb.append(",ITCLASSIFICACAOFISCAL=" + ITCLASSIFICACAOFISCAL);
			sb.append(",ITSITTRIBUTARIAFEDERAL=" + ITSITTRIBUTARIAFEDERAL);
			sb.append(",ITQTDEPRODUTO=" + ITQTDEPRODUTO);
			sb.append(",ITUNIDADEMEDIDA=" + ITUNIDADEMEDIDA);
			sb.append(",ITVALORPRECOUNITARIO=" + ITVALORPRECOUNITARIO);
			sb.append(",ITVALORPRECOTOTAL=" + ITVALORPRECOTOTAL);
			sb.append(",ITVALORDESCONTO=" + ITVALORDESCONTO);
			sb.append(",ITDESPESASFRETE=" + ITDESPESASFRETE);
			sb.append(",ITDESPESASSEGURO=" + ITDESPESASSEGURO);
			sb.append(",ITOUTRASDESPESAS=" + ITOUTRASDESPESAS);
			sb.append(",ITINDMOVIMENTACAO=" + ITINDMOVIMENTACAO);
			sb.append(",ITVALORBASEICMS=" + ITVALORBASEICMS);
			sb.append(",ITALIQUOTAICMS=" + ITALIQUOTAICMS);
			sb.append(",ITVALORICMS=" + ITVALORICMS);
			sb.append(",ITVALORISENTOICM=" + ITVALORISENTOICM);
			sb.append(",ITVALOROUTROICM=" + ITVALOROUTROICM);
			sb.append(",ITVALORCONTABILICM=" + ITVALORCONTABILICM);
			sb.append(",FABASEPIS=" + FABASEPIS);
			sb.append(",ITBASEPIS=" + ITBASEPIS);
			sb.append(",ITALIQUOTAPIS=" + ITALIQUOTAPIS);
			sb.append(",ITVALORPIS=" + ITVALORPIS);
			sb.append(",FABASECOFINS=" + FABASECOFINS);
			sb.append(",ITBASECOFINS=" + ITBASECOFINS);
			sb.append(",ITALIQUOTACOFINS=" + ITALIQUOTACOFINS);
			sb.append(",ITVALORCOFINS=" + ITVALORCOFINS);
			sb.append(",ITDESCRCOMPLEMENTAR=" + ITDESCRCOMPLEMENTAR);
			sb.append(",ITPRECOTOTAL=" + ITPRECOTOTAL);
			sb.append(",ITSITUACAOTRIBUTARIAESTADUAL="
					+ ITSITUACAOTRIBUTARIAESTADUAL);
			sb.append(",ITSITTRIBCOFINS=" + ITSITTRIBCOFINS);
			sb.append(",ITSITTRIBPIS=" + ITSITTRIBPIS);
			sb.append(",ITCONTAANALITICA=" + ITCONTAANALITICA);
			sb.append(",FAINDMOVIMENTO=" + FAINDMOVIMENTO);
			sb.append(",ITUNIDADEPADRAO=" + ITUNIDADEPADRAO);
			sb.append(",ITINDTRIBUTACAOIPI=" + ITINDTRIBUTACAOIPI);
			sb.append(",ITVALORCONTABILIPI=" + ITVALORCONTABILIPI);
			sb.append(",ITVALORTOTALDOCUMENTO=" + ITVALORTOTALDOCUMENTO);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(NFEPRODUTOStruct other) {

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

				NFEPRODUTOStruct NFEPRODUTO = new NFEPRODUTOStruct();
				outSMARTStruct outSMART = new outSMARTStruct();
				row1Struct row1 = new row1Struct();
				row2Struct row2 = new row2Struct();
				row2Struct row3 = row2;

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
						+ "] ([FACODIGOEMPRESA],[FAEMITENTE],[FAMODELODOCUMENTO],[FATIPODOCUMENTO],[FASERIEDOCUMENTO],[FANUMERODOCUMENTO],[FANFECHAVE],[FADATAEMISSAO],[FADATASAIDA],[FAHORASAIDA],[FACODIGOCLIENTE],[FAUFCLIENTE],[FASITUACAODOC],[FAINDCANCELAMENTO],[FACONSUMIDOR],[FACONTRIBUINTE],[FAVALORPRODUTOS],[FATIPOFATURA],[FATOTALNFISCAL],[FABASEICMS],[FATOTALICM],[FABASEREDUCAOIPI],[FAVALORTOTALIPI],[FATOTALBASESUBST],[FATOTALICMSUBST],[FAINSCRICAOSUBSTITUTO],[FAVALORFRETE],[FAVALORSEGURO],[FAVALOROUTRAS],[FAVIATRANSPORTE],[FAMODALIDADEFRETE],[FACODIGOTRANSPORTADOR],[FAQTDEVOLUMES],[FAESPECIEVOLUMES],[FAMARCAVOLUMES],[FANUMERACAOVOLUMES],[FAPESOBRUTO],[FAPESOLIQUIDO],[FAIDENTIFICACAOVEICULO],[FATIPONOTA],[ITNUMEROITEM],[ITCODIGOPRODUTO],[ITCFOP],[ITCLASSIFICACAOFISCAL],[ITSITTRIBUTARIAFEDERAL],[ITQTDEPRODUTO],[ITUNIDADEMEDIDA],[ITVALORPRECOUNITARIO],[ITVALORPRECOTOTAL],[ITVALORDESCONTO],[ITDESPESASFRETE],[ITDESPESASSEGURO],[ITOUTRASDESPESAS],[ITINDMOVIMENTACAO],[ITVALORBASEICMS],[ITALIQUOTAICMS],[ITVALORICMS],[ITVALORISENTOICM],[ITVALOROUTROICM],[ITVALORCONTABILICM],[FABASEPIS],[ITBASEPIS],[ITALIQUOTAPIS],[ITVALORPIS],[FABASECOFINS],[ITBASECOFINS],[ITALIQUOTACOFINS],[ITVALORCOFINS],[ITDESCRCOMPLEMENTAR],[ITPRECOTOTAL],[ITSITUACAOTRIBUTARIAESTADUAL],[ITSITTRIBCOFINS],[ITSITTRIBPIS],[ITCONTAANALITICA],[FAINDMOVIMENTO],[ITUNIDADEPADRAO],[ITINDTRIBUTACAOIPI],[ITVALORCONTABILIPI],[ITVALORTOTALDOCUMENTO]) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				java.sql.PreparedStatement pstmt_tMSSqlOutput_1 = conn_tMSSqlOutput_1
						.prepareStatement(insert_tMSSqlOutput_1);

				/**
				 * [tMSSqlOutput_1 begin ] stop
				 */

				/**
				 * [tLogRow_1 begin ] start
				 */

				ok_Hash.put("tLogRow_1", false);
				start_Hash.put("tLogRow_1", System.currentTimeMillis());

				currentComponent = "tLogRow_1";

				int tos_count_tLogRow_1 = 0;

				// /////////////////////

				class Util_tLogRow_1 {

					String[] des_top = { ".", ".", "-", "+" };

					String[] des_head = { "|=", "=|", "-", "+" };

					String[] des_bottom = { "'", "'", "-", "+" };

					String name = "";

					java.util.List<String[]> list = new java.util.ArrayList<String[]>();

					int[] colLengths = new int[79];

					public void addRow(String[] row) {

						for (int i = 0; i < 79; i++) {
							if (row[i] != null) {
								colLengths[i] = Math.max(colLengths[i],
										row[i].length());
							}
						}
						list.add(row);
					}

					public void setTableName(String name) {

						this.name = name;
					}

					public StringBuilder format() {

						StringBuilder sb = new StringBuilder();

						sb.append(print(des_top));

						int totals = 0;
						for (int i = 0; i < colLengths.length; i++) {
							totals = totals + colLengths[i];
						}

						// name
						sb.append("|");
						int k = 0;
						for (k = 0; k < (totals + 78 - name.length()) / 2; k++) {
							sb.append(' ');
						}
						sb.append(name);
						for (int i = 0; i < totals + 78 - name.length() - k; i++) {
							sb.append(' ');
						}
						sb.append("|\n");

						// head and rows
						sb.append(print(des_head));
						for (int i = 0; i < list.size(); i++) {

							String[] row = list.get(i);

							java.util.Formatter formatter = new java.util.Formatter(
									new StringBuilder());

							StringBuilder sbformat = new StringBuilder();
							sbformat.append("|%1$-");
							sbformat.append(colLengths[0]);
							sbformat.append("s");

							sbformat.append("|%2$-");
							sbformat.append(colLengths[1]);
							sbformat.append("s");

							sbformat.append("|%3$-");
							sbformat.append(colLengths[2]);
							sbformat.append("s");

							sbformat.append("|%4$-");
							sbformat.append(colLengths[3]);
							sbformat.append("s");

							sbformat.append("|%5$-");
							sbformat.append(colLengths[4]);
							sbformat.append("s");

							sbformat.append("|%6$-");
							sbformat.append(colLengths[5]);
							sbformat.append("s");

							sbformat.append("|%7$-");
							sbformat.append(colLengths[6]);
							sbformat.append("s");

							sbformat.append("|%8$-");
							sbformat.append(colLengths[7]);
							sbformat.append("s");

							sbformat.append("|%9$-");
							sbformat.append(colLengths[8]);
							sbformat.append("s");

							sbformat.append("|%10$-");
							sbformat.append(colLengths[9]);
							sbformat.append("s");

							sbformat.append("|%11$-");
							sbformat.append(colLengths[10]);
							sbformat.append("s");

							sbformat.append("|%12$-");
							sbformat.append(colLengths[11]);
							sbformat.append("s");

							sbformat.append("|%13$-");
							sbformat.append(colLengths[12]);
							sbformat.append("s");

							sbformat.append("|%14$-");
							sbformat.append(colLengths[13]);
							sbformat.append("s");

							sbformat.append("|%15$-");
							sbformat.append(colLengths[14]);
							sbformat.append("s");

							sbformat.append("|%16$-");
							sbformat.append(colLengths[15]);
							sbformat.append("s");

							sbformat.append("|%17$-");
							sbformat.append(colLengths[16]);
							sbformat.append("s");

							sbformat.append("|%18$-");
							sbformat.append(colLengths[17]);
							sbformat.append("s");

							sbformat.append("|%19$-");
							sbformat.append(colLengths[18]);
							sbformat.append("s");

							sbformat.append("|%20$-");
							sbformat.append(colLengths[19]);
							sbformat.append("s");

							sbformat.append("|%21$-");
							sbformat.append(colLengths[20]);
							sbformat.append("s");

							sbformat.append("|%22$-");
							sbformat.append(colLengths[21]);
							sbformat.append("s");

							sbformat.append("|%23$-");
							sbformat.append(colLengths[22]);
							sbformat.append("s");

							sbformat.append("|%24$-");
							sbformat.append(colLengths[23]);
							sbformat.append("s");

							sbformat.append("|%25$-");
							sbformat.append(colLengths[24]);
							sbformat.append("s");

							sbformat.append("|%26$-");
							sbformat.append(colLengths[25]);
							sbformat.append("s");

							sbformat.append("|%27$-");
							sbformat.append(colLengths[26]);
							sbformat.append("s");

							sbformat.append("|%28$-");
							sbformat.append(colLengths[27]);
							sbformat.append("s");

							sbformat.append("|%29$-");
							sbformat.append(colLengths[28]);
							sbformat.append("s");

							sbformat.append("|%30$-");
							sbformat.append(colLengths[29]);
							sbformat.append("s");

							sbformat.append("|%31$-");
							sbformat.append(colLengths[30]);
							sbformat.append("s");

							sbformat.append("|%32$-");
							sbformat.append(colLengths[31]);
							sbformat.append("s");

							sbformat.append("|%33$-");
							sbformat.append(colLengths[32]);
							sbformat.append("s");

							sbformat.append("|%34$-");
							sbformat.append(colLengths[33]);
							sbformat.append("s");

							sbformat.append("|%35$-");
							sbformat.append(colLengths[34]);
							sbformat.append("s");

							sbformat.append("|%36$-");
							sbformat.append(colLengths[35]);
							sbformat.append("s");

							sbformat.append("|%37$-");
							sbformat.append(colLengths[36]);
							sbformat.append("s");

							sbformat.append("|%38$-");
							sbformat.append(colLengths[37]);
							sbformat.append("s");

							sbformat.append("|%39$-");
							sbformat.append(colLengths[38]);
							sbformat.append("s");

							sbformat.append("|%40$-");
							sbformat.append(colLengths[39]);
							sbformat.append("s");

							sbformat.append("|%41$-");
							sbformat.append(colLengths[40]);
							sbformat.append("s");

							sbformat.append("|%42$-");
							sbformat.append(colLengths[41]);
							sbformat.append("s");

							sbformat.append("|%43$-");
							sbformat.append(colLengths[42]);
							sbformat.append("s");

							sbformat.append("|%44$-");
							sbformat.append(colLengths[43]);
							sbformat.append("s");

							sbformat.append("|%45$-");
							sbformat.append(colLengths[44]);
							sbformat.append("s");

							sbformat.append("|%46$-");
							sbformat.append(colLengths[45]);
							sbformat.append("s");

							sbformat.append("|%47$-");
							sbformat.append(colLengths[46]);
							sbformat.append("s");

							sbformat.append("|%48$-");
							sbformat.append(colLengths[47]);
							sbformat.append("s");

							sbformat.append("|%49$-");
							sbformat.append(colLengths[48]);
							sbformat.append("s");

							sbformat.append("|%50$-");
							sbformat.append(colLengths[49]);
							sbformat.append("s");

							sbformat.append("|%51$-");
							sbformat.append(colLengths[50]);
							sbformat.append("s");

							sbformat.append("|%52$-");
							sbformat.append(colLengths[51]);
							sbformat.append("s");

							sbformat.append("|%53$-");
							sbformat.append(colLengths[52]);
							sbformat.append("s");

							sbformat.append("|%54$-");
							sbformat.append(colLengths[53]);
							sbformat.append("s");

							sbformat.append("|%55$-");
							sbformat.append(colLengths[54]);
							sbformat.append("s");

							sbformat.append("|%56$-");
							sbformat.append(colLengths[55]);
							sbformat.append("s");

							sbformat.append("|%57$-");
							sbformat.append(colLengths[56]);
							sbformat.append("s");

							sbformat.append("|%58$-");
							sbformat.append(colLengths[57]);
							sbformat.append("s");

							sbformat.append("|%59$-");
							sbformat.append(colLengths[58]);
							sbformat.append("s");

							sbformat.append("|%60$-");
							sbformat.append(colLengths[59]);
							sbformat.append("s");

							sbformat.append("|%61$-");
							sbformat.append(colLengths[60]);
							sbformat.append("s");

							sbformat.append("|%62$-");
							sbformat.append(colLengths[61]);
							sbformat.append("s");

							sbformat.append("|%63$-");
							sbformat.append(colLengths[62]);
							sbformat.append("s");

							sbformat.append("|%64$-");
							sbformat.append(colLengths[63]);
							sbformat.append("s");

							sbformat.append("|%65$-");
							sbformat.append(colLengths[64]);
							sbformat.append("s");

							sbformat.append("|%66$-");
							sbformat.append(colLengths[65]);
							sbformat.append("s");

							sbformat.append("|%67$-");
							sbformat.append(colLengths[66]);
							sbformat.append("s");

							sbformat.append("|%68$-");
							sbformat.append(colLengths[67]);
							sbformat.append("s");

							sbformat.append("|%69$-");
							sbformat.append(colLengths[68]);
							sbformat.append("s");

							sbformat.append("|%70$-");
							sbformat.append(colLengths[69]);
							sbformat.append("s");

							sbformat.append("|%71$-");
							sbformat.append(colLengths[70]);
							sbformat.append("s");

							sbformat.append("|%72$-");
							sbformat.append(colLengths[71]);
							sbformat.append("s");

							sbformat.append("|%73$-");
							sbformat.append(colLengths[72]);
							sbformat.append("s");

							sbformat.append("|%74$-");
							sbformat.append(colLengths[73]);
							sbformat.append("s");

							sbformat.append("|%75$-");
							sbformat.append(colLengths[74]);
							sbformat.append("s");

							sbformat.append("|%76$-");
							sbformat.append(colLengths[75]);
							sbformat.append("s");

							sbformat.append("|%77$-");
							sbformat.append(colLengths[76]);
							sbformat.append("s");

							sbformat.append("|%78$-");
							sbformat.append(colLengths[77]);
							sbformat.append("s");

							sbformat.append("|%79$-");
							sbformat.append(colLengths[78]);
							sbformat.append("s");

							sbformat.append("|\n");

							formatter.format(sbformat.toString(),
									(Object[]) row);

							sb.append(formatter.toString());
							if (i == 0)
								sb.append(print(des_head)); // print the head
						}

						// end
						sb.append(print(des_bottom));
						return sb;
					}

					private StringBuilder print(String[] fillChars) {
						StringBuilder sb = new StringBuilder();
						// first column
						sb.append(fillChars[0]);
						for (int i = 0; i < colLengths[0]
								- fillChars[0].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						for (int i = 0; i < colLengths[1]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[2]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[3]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[4]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[5]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[6]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[7]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[8]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[9]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[10]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[11]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[12]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[13]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[14]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[15]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[16]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[17]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[18]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[19]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[20]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[21]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[22]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[23]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[24]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[25]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[26]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[27]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[28]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[29]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[30]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[31]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[32]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[33]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[34]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[35]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[36]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[37]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[38]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[39]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[40]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[41]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[42]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[43]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[44]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[45]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[46]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[47]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[48]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[49]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[50]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[51]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[52]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[53]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[54]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[55]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[56]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[57]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[58]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[59]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[60]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[61]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[62]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[63]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[64]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[65]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[66]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[67]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[68]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[69]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[70]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[71]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[72]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[73]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[74]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[75]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[76]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[77]
								- fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						// last column
						for (int i = 0; i < colLengths[78]
								- fillChars[1].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[1]);
						sb.append("\n");
						return sb;
					}

					public boolean isTableEmpty() {
						if (list.size() > 1)
							return false;
						return true;
					}
				}
				Util_tLogRow_1 util_tLogRow_1 = new Util_tLogRow_1();
				util_tLogRow_1.setTableName("tLogRow_1");
				util_tLogRow_1.addRow(new String[] { "FACODIGOEMPRESA",
						"FAEMITENTE", "FAMODELODOCUMENTO", "FATIPODOCUMENTO",
						"FASERIEDOCUMENTO", "FANUMERODOCUMENTO", "FANFECHAVE",
						"FADATAEMISSAO", "FADATASAIDA", "FAHORASAIDA",
						"FACODIGOCLIENTE", "FAUFCLIENTE", "FASITUACAODOC",
						"FAINDCANCELAMENTO", "FACONSUMIDOR", "FACONTRIBUINTE",
						"FAVALORPRODUTOS", "FATIPOFATURA", "FATOTALNFISCAL",
						"FABASEICMS", "FATOTALICM", "FABASEREDUCAOIPI",
						"FAVALORTOTALIPI", "FATOTALBASESUBST",
						"FATOTALICMSUBST", "FAINSCRICAOSUBSTITUTO",
						"FAVALORFRETE", "FAVALORSEGURO", "FAVALOROUTRAS",
						"FAVIATRANSPORTE", "FAMODALIDADEFRETE",
						"FACODIGOTRANSPORTADOR", "FAQTDEVOLUMES",
						"FAESPECIEVOLUMES", "FAMARCAVOLUMES",
						"FANUMERACAOVOLUMES", "FAPESOBRUTO", "FAPESOLIQUIDO",
						"FAIDENTIFICACAOVEICULO", "FATIPONOTA", "ITNUMEROITEM",
						"ITCODIGOPRODUTO", "ITCFOP", "ITCLASSIFICACAOFISCAL",
						"ITSITTRIBUTARIAFEDERAL", "ITQTDEPRODUTO",
						"ITUNIDADEMEDIDA", "ITVALORPRECOUNITARIO",
						"ITVALORPRECOTOTAL", "ITVALORDESCONTO",
						"ITDESPESASFRETE", "ITDESPESASSEGURO",
						"ITOUTRASDESPESAS", "ITINDMOVIMENTACAO",
						"ITVALORBASEICMS", "ITALIQUOTAICMS", "ITVALORICMS",
						"ITVALORISENTOICM", "ITVALOROUTROICM",
						"ITVALORCONTABILICM", "FABASEPIS", "ITBASEPIS",
						"ITALIQUOTAPIS", "ITVALORPIS", "FABASECOFINS",
						"ITBASECOFINS", "ITALIQUOTACOFINS", "ITVALORCOFINS",
						"ITDESCRCOMPLEMENTAR", "ITPRECOTOTAL",
						"ITSITUACAOTRIBUTARIAESTADUAL", "ITSITTRIBCOFINS",
						"ITSITTRIBPIS", "ITCONTAANALITICA", "FAINDMOVIMENTO",
						"ITUNIDADEPADRAO", "ITINDTRIBUTACAOIPI",
						"ITVALORCONTABILIPI", "ITVALORTOTALDOCUMENTO", });
				StringBuilder strBuffer_tLogRow_1 = null;
				int nb_line_tLogRow_1 = 0;
				// /////////////////////

				/**
				 * [tLogRow_1 begin ] stop
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
						output_row.FACODIGOEMPRESA = (String) coalesce(
								input_row.FACODIGOEMPRESA, ' ', null, "");
						output_row.FAEMITENTE = (String) coalesce(
								input_row.FAEMITENTE, ' ', null, "");
						output_row.FAMODELODOCUMENTO = (String) coalesce(
								input_row.FAMODELODOCUMENTO, ' ', null, "");
						output_row.FATIPODOCUMENTO = (String) coalesce(
								input_row.FATIPODOCUMENTO, ' ', null, "");
						output_row.FASERIEDOCUMENTO = (String) coalesce(
								input_row.FASERIEDOCUMENTO, ' ', null, "");
						output_row.FANUMERODOCUMENTO = (String) coalesce(
								input_row.FANUMERODOCUMENTO, ' ', null, "");
						output_row.FANFECHAVE = (String) coalesce(
								input_row.FANFECHAVE, ' ', null, "");
						output_row.FADATAEMISSAO = (java.util.Date) coalesce(
								input_row.FADATAEMISSAO, TalendDate.parseDate(
										"yyyy-MM-dd", "1900-01-01"), null,
								TalendDate
										.parseDate("yyyy-MM-dd", "1970-01-01"));
						output_row.FADATASAIDA = (java.util.Date) coalesce(
								input_row.FADATASAIDA, TalendDate.parseDate(
										"yyyy-MM-dd", "1900-01-01"), null,
								TalendDate
										.parseDate("yyyy-MM-dd", "1970-01-01"));
						output_row.FAHORASAIDA = (String) coalesce(
								input_row.FAHORASAIDA, ' ', null, "");
						output_row.FACODIGOCLIENTE = (String) coalesce(
								input_row.FACODIGOCLIENTE, ' ', null, "");
						output_row.FAUFCLIENTE = (String) coalesce(
								input_row.FAUFCLIENTE, ' ', null, "");
						output_row.FASITUACAODOC = (String) coalesce(
								input_row.FASITUACAODOC, ' ', null, "");
						output_row.FAINDCANCELAMENTO = (String) coalesce(
								input_row.FAINDCANCELAMENTO, ' ', null, "");
						output_row.FACONSUMIDOR = (String) coalesce(
								input_row.FACONSUMIDOR, ' ', null, "");
						output_row.FACONTRIBUINTE = (String) coalesce(
								input_row.FACONTRIBUINTE, ' ', null, "");
						output_row.FAVALORPRODUTOS = (String) coalesce(
								input_row.FAVALORPRODUTOS, ' ', null, "");
						output_row.FATIPOFATURA = (String) coalesce(
								input_row.FATIPOFATURA, ' ', null, "");
						output_row.FATOTALNFISCAL = (String) coalesce(
								input_row.FATOTALNFISCAL, ' ', null, "");
						output_row.FABASEICMS = (String) coalesce(
								input_row.FABASEICMS, ' ', null, "");
						output_row.FATOTALICM = (String) coalesce(
								input_row.FATOTALICM, ' ', null, "");
						output_row.FABASEREDUCAOIPI = (String) coalesce(
								input_row.FABASEREDUCAOIPI, ' ', null, "");
						output_row.FAVALORTOTALIPI = (String) coalesce(
								input_row.FAVALORTOTALIPI, ' ', null, "");
						output_row.FATOTALBASESUBST = (String) coalesce(
								input_row.FATOTALBASESUBST, ' ', null, "");
						output_row.FATOTALICMSUBST = (String) coalesce(
								input_row.FATOTALICMSUBST, ' ', null, "");
						output_row.FAINSCRICAOSUBSTITUTO = (String) coalesce(
								input_row.FAINSCRICAOSUBSTITUTO, ' ', null, "");
						output_row.FAVALORFRETE = (String) coalesce(
								input_row.FAVALORFRETE, ' ', null, "");
						output_row.FAVALORSEGURO = (String) coalesce(
								input_row.FAVALORSEGURO, ' ', null, "");
						output_row.FAVALOROUTRAS = (String) coalesce(
								input_row.FAVALOROUTRAS, ' ', null, "");
						output_row.FAVIATRANSPORTE = (String) coalesce(
								input_row.FAVIATRANSPORTE, ' ', null, "");
						output_row.FAMODALIDADEFRETE = (String) coalesce(
								input_row.FAMODALIDADEFRETE, ' ', null, "");
						output_row.FACODIGOTRANSPORTADOR = (String) coalesce(
								input_row.FACODIGOTRANSPORTADOR, ' ', null, "");
						output_row.FAQTDEVOLUMES = (String) coalesce(
								input_row.FAQTDEVOLUMES, ' ', null, "");
						output_row.FAESPECIEVOLUMES = (String) coalesce(
								input_row.FAESPECIEVOLUMES, ' ', null, "");
						output_row.FAMARCAVOLUMES = (String) coalesce(
								input_row.FAMARCAVOLUMES, ' ', null, "");
						output_row.FANUMERACAOVOLUMES = (String) coalesce(
								input_row.FANUMERACAOVOLUMES, ' ', null, "");
						output_row.FAPESOBRUTO = (String) coalesce(
								input_row.FAPESOBRUTO, ' ', null, "");
						output_row.FAPESOLIQUIDO = (String) coalesce(
								input_row.FAPESOLIQUIDO, ' ', null, "");
						output_row.FAIDENTIFICACAOVEICULO = (String) coalesce(
								input_row.FAIDENTIFICACAOVEICULO, ' ', null, "");
						output_row.FATIPONOTA = (String) coalesce(
								input_row.FATIPONOTA, ' ', null, "");
						output_row.ITNUMEROITEM = (String) coalesce(
								input_row.ITNUMEROITEM, ' ', null, "");
						output_row.ITCODIGOPRODUTO = (String) coalesce(
								input_row.ITCODIGOPRODUTO, ' ', null, "");
						output_row.ITCFOP = (String) coalesce(input_row.ITCFOP,
								' ', null, "");
						output_row.ITCLASSIFICACAOFISCAL = (String) coalesce(
								input_row.ITCLASSIFICACAOFISCAL, ' ', null, "");
						output_row.ITSITTRIBUTARIAFEDERAL = (String) coalesce(
								input_row.ITSITTRIBUTARIAFEDERAL, ' ', null, "");
						output_row.ITQTDEPRODUTO = (String) coalesce(
								input_row.ITQTDEPRODUTO, ' ', null, "");
						output_row.ITUNIDADEMEDIDA = (String) coalesce(
								input_row.ITUNIDADEMEDIDA, ' ', null, "");
						output_row.ITVALORPRECOUNITARIO = (String) coalesce(
								input_row.ITVALORPRECOUNITARIO, ' ', null, "");
						output_row.ITVALORPRECOTOTAL = (String) coalesce(
								input_row.ITVALORPRECOTOTAL, ' ', null, "");
						output_row.ITVALORDESCONTO = (String) coalesce(
								input_row.ITVALORDESCONTO, ' ', null, "");
						output_row.ITDESPESASFRETE = (String) coalesce(
								input_row.ITDESPESASFRETE, ' ', null, "");
						output_row.ITDESPESASSEGURO = (String) coalesce(
								input_row.ITDESPESASSEGURO, ' ', null, "");
						output_row.ITOUTRASDESPESAS = (String) coalesce(
								input_row.ITOUTRASDESPESAS, ' ', null, "");
						output_row.ITINDMOVIMENTACAO = (String) coalesce(
								input_row.ITINDMOVIMENTACAO, ' ', null, "");
						output_row.ITVALORBASEICMS = (String) coalesce(
								input_row.ITVALORBASEICMS, ' ', null, "");
						output_row.ITALIQUOTAICMS = (String) coalesce(
								input_row.ITALIQUOTAICMS, ' ', null, "");
						output_row.ITVALORICMS = (String) coalesce(
								input_row.ITVALORICMS, ' ', null, "");
						output_row.ITVALORISENTOICM = (String) coalesce(
								input_row.ITVALORISENTOICM, ' ', null, "");
						output_row.ITVALOROUTROICM = (String) coalesce(
								input_row.ITVALOROUTROICM, ' ', null, "");
						output_row.ITVALORCONTABILICM = (String) coalesce(
								input_row.ITVALORCONTABILICM, ' ', null, "");
						output_row.FABASEPIS = (String) coalesce(
								input_row.FABASEPIS, ' ', null, "");
						output_row.ITBASEPIS = (String) coalesce(
								input_row.ITBASEPIS, ' ', null, "");
						output_row.ITALIQUOTAPIS = (String) coalesce(
								input_row.ITALIQUOTAPIS, ' ', null, "");
						output_row.ITVALORPIS = (String) coalesce(
								input_row.ITVALORPIS, ' ', null, "");
						output_row.FABASECOFINS = (String) coalesce(
								input_row.FABASECOFINS, ' ', null, "");
						output_row.ITBASECOFINS = (String) coalesce(
								input_row.ITBASECOFINS, ' ', null, "");
						output_row.ITALIQUOTACOFINS = (String) coalesce(
								input_row.ITALIQUOTACOFINS, ' ', null, "");
						output_row.ITVALORCOFINS = (String) coalesce(
								input_row.ITVALORCOFINS, ' ', null, "");
						output_row.ITDESCRCOMPLEMENTAR = (String) coalesce(
								input_row.ITDESCRCOMPLEMENTAR, ' ', null, "");
						output_row.ITPRECOTOTAL = (String) coalesce(
								input_row.ITPRECOTOTAL, ' ', null, "");
						output_row.ITSITUACAOTRIBUTARIAESTADUAL = (String) coalesce(
								input_row.ITSITUACAOTRIBUTARIAESTADUAL, ' ',
								null, "");
						output_row.ITSITTRIBCOFINS = (String) coalesce(
								input_row.ITSITTRIBCOFINS, ' ', null, "");
						output_row.ITSITTRIBPIS = (String) coalesce(
								input_row.ITSITTRIBPIS, ' ', null, "");
						output_row.ITCONTAANALITICA = (String) coalesce(
								input_row.ITCONTAANALITICA, ' ', null, "");
						output_row.FAINDMOVIMENTO = (String) coalesce(
								input_row.FAINDMOVIMENTO, ' ', null, "");
						output_row.ITUNIDADEPADRAO = (String) coalesce(
								input_row.ITUNIDADEPADRAO, ' ', null, "");
						output_row.ITINDTRIBUTACAOIPI = (String) coalesce(
								input_row.ITINDTRIBUTACAOIPI, ' ', null, "");
						output_row.ITVALORCONTABILIPI = (String) coalesce(
								input_row.ITVALORCONTABILIPI, ' ', null, "");
						output_row.ITVALORTOTALDOCUMENTO = (String) coalesce(
								input_row.ITVALORTOTALDOCUMENTO, ' ', null, "");
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
						.getFunctionTemplate("ZMSAF_FIS_NFSPROD");
				if (functionTemplate_tSAPInput_1 == null) {
					com.sap.conn.jco.JCoContext.end(dest_tSAPInput_1);
					throw new RuntimeException(
							"The RFC can't support the function: "
									+ "ZMSAF_FIS_NFSPROD" + ".");
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

				boolean go_NFEPRODUTO = true;

				com.sap.conn.jco.JCoTable table_NFEPRODUTO_tSAPInput_1 = tableParameterList_tSAPInput_1
						.getTable("T_DATOS");

				go_NFEPRODUTO = !table_NFEPRODUTO_tSAPInput_1.isEmpty();

				while (

				go_NFEPRODUTO) {

					NFEPRODUTO = null;

					if (go_NFEPRODUTO) {

						if (table_NFEPRODUTO_tSAPInput_1.isLastRow()) { // check
																		// the
																		// flag
																		// first
							go_NFEPRODUTO = false;
						}
						NFEPRODUTO = new NFEPRODUTOStruct();

						// "table_output" or "output_table"--FACODIGOEMPRESA

						NFEPRODUTO.FACODIGOEMPRESA = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FACODIGOEMPRESA"));

						// "table_output" or "output_table"--FAEMITENTE

						NFEPRODUTO.FAEMITENTE = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FAEMITENTE"));

						// "table_output" or "output_table"--FAMODELODOCUMENTO

						NFEPRODUTO.FAMODELODOCUMENTO = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FAMODELODOCUMENTO"));

						// "table_output" or "output_table"--FATIPODOCUMENTO

						NFEPRODUTO.FATIPODOCUMENTO = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FATIPODOCUMENTO"));

						// "table_output" or "output_table"--FASERIEDOCUMENTO

						NFEPRODUTO.FASERIEDOCUMENTO = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FASERIEDOCUMENTO"));

						// "table_output" or "output_table"--FANUMERODOCUMENTO

						NFEPRODUTO.FANUMERODOCUMENTO = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FANUMERODOCUMENTO"));

						// "table_output" or "output_table"--FANFECHAVE

						NFEPRODUTO.FANFECHAVE = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FANFECHAVE"));

						// "table_output" or "output_table"--FADATAEMISSAO

						//
						NFEPRODUTO.FADATAEMISSAO = ParserUtils
								.parseTo_Date(table_NFEPRODUTO_tSAPInput_1
										.getString("FADATAEMISSAO"), "yy-MM-dd");

						// "table_output" or "output_table"--FADATASAIDA

						//
						NFEPRODUTO.FADATASAIDA = ParserUtils.parseTo_Date(
								table_NFEPRODUTO_tSAPInput_1
										.getString("FADATASAIDA"), "yy-MM-dd");

						// "table_output" or "output_table"--FAHORASAIDA

						NFEPRODUTO.FAHORASAIDA = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FAHORASAIDA"));

						// "table_output" or "output_table"--FACODIGOCLIENTE

						NFEPRODUTO.FACODIGOCLIENTE = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FACODIGOCLIENTE"));

						// "table_output" or "output_table"--FAUFCLIENTE

						NFEPRODUTO.FAUFCLIENTE = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FAUFCLIENTE"));

						// "table_output" or "output_table"--FASITUACAODOC

						NFEPRODUTO.FASITUACAODOC = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FASITUACAODOC"));

						// "table_output" or "output_table"--FAINDCANCELAMENTO

						NFEPRODUTO.FAINDCANCELAMENTO = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FAINDCANCELAMENTO"));

						// "table_output" or "output_table"--FACONSUMIDOR

						NFEPRODUTO.FACONSUMIDOR = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FACONSUMIDOR"));

						// "table_output" or "output_table"--FACONTRIBUINTE

						NFEPRODUTO.FACONTRIBUINTE = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FACONTRIBUINTE"));

						// "table_output" or "output_table"--FAVALORPRODUTOS

						NFEPRODUTO.FAVALORPRODUTOS = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FAVALORPRODUTOS"));

						// "table_output" or "output_table"--FATIPOFATURA

						NFEPRODUTO.FATIPOFATURA = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FATIPOFATURA"));

						// "table_output" or "output_table"--FATOTALNFISCAL

						NFEPRODUTO.FATOTALNFISCAL = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FATOTALNFISCAL"));

						// "table_output" or "output_table"--FABASEICMS

						NFEPRODUTO.FABASEICMS = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FABASEICMS"));

						// "table_output" or "output_table"--FATOTALICM

						NFEPRODUTO.FATOTALICM = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FATOTALICM"));

						// "table_output" or "output_table"--FABASEREDUCAOIPI

						NFEPRODUTO.FABASEREDUCAOIPI = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FABASEREDUCAOIPI"));

						// "table_output" or "output_table"--FAVALORTOTALIPI

						NFEPRODUTO.FAVALORTOTALIPI = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FAVALORTOTALIPI"));

						// "table_output" or "output_table"--FATOTALBASESUBST

						NFEPRODUTO.FATOTALBASESUBST = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FATOTALBASESUBST"));

						// "table_output" or "output_table"--FATOTALICMSUBST

						NFEPRODUTO.FATOTALICMSUBST = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FATOTALICMSUBST"));

						// "table_output" or
						// "output_table"--FAINSCRICAOSUBSTITUTO

						NFEPRODUTO.FAINSCRICAOSUBSTITUTO = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FAINSCRICAOSUBSTITUTO"));

						// "table_output" or "output_table"--FAVALORFRETE

						NFEPRODUTO.FAVALORFRETE = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FAVALORFRETE"));

						// "table_output" or "output_table"--FAVALORSEGURO

						NFEPRODUTO.FAVALORSEGURO = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FAVALORSEGURO"));

						// "table_output" or "output_table"--FAVALOROUTRAS

						NFEPRODUTO.FAVALOROUTRAS = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FAVALOROUTRAS"));

						// "table_output" or "output_table"--FAVIATRANSPORTE

						NFEPRODUTO.FAVIATRANSPORTE = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FAVIATRANSPORTE"));

						// "table_output" or "output_table"--FAMODALIDADEFRETE

						NFEPRODUTO.FAMODALIDADEFRETE = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FAMODALIDADEFRETE"));

						// "table_output" or
						// "output_table"--FACODIGOTRANSPORTADOR

						NFEPRODUTO.FACODIGOTRANSPORTADOR = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FACODIGOTRANSPORTADOR"));

						// "table_output" or "output_table"--FAQTDEVOLUMES

						NFEPRODUTO.FAQTDEVOLUMES = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FAQTDEVOLUMES"));

						// "table_output" or "output_table"--FAESPECIEVOLUMES

						NFEPRODUTO.FAESPECIEVOLUMES = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FAESPECIEVOLUMES"));

						// "table_output" or "output_table"--FAMARCAVOLUMES

						NFEPRODUTO.FAMARCAVOLUMES = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FAMARCAVOLUMES"));

						// "table_output" or "output_table"--FANUMERACAOVOLUMES

						NFEPRODUTO.FANUMERACAOVOLUMES = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FANUMERACAOVOLUMES"));

						// "table_output" or "output_table"--FAPESOBRUTO

						NFEPRODUTO.FAPESOBRUTO = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FAPESOBRUTO"));

						// "table_output" or "output_table"--FAPESOLIQUIDO

						NFEPRODUTO.FAPESOLIQUIDO = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FAPESOLIQUIDO"));

						// "table_output" or
						// "output_table"--FAIDENTIFICACAOVEICULO

						NFEPRODUTO.FAIDENTIFICACAOVEICULO = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FAIDENTIFICACAOVEICULO"));

						// "table_output" or "output_table"--FATIPONOTA

						NFEPRODUTO.FATIPONOTA = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FATIPONOTA"));

						// "table_output" or "output_table"--ITNUMEROITEM

						NFEPRODUTO.ITNUMEROITEM = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITNUMEROITEM"));

						// "table_output" or "output_table"--ITCODIGOPRODUTO

						NFEPRODUTO.ITCODIGOPRODUTO = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITCODIGOPRODUTO"));

						// "table_output" or "output_table"--ITCFOP

						NFEPRODUTO.ITCFOP = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITCFOP"));

						// "table_output" or
						// "output_table"--ITCLASSIFICACAOFISCAL

						NFEPRODUTO.ITCLASSIFICACAOFISCAL = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITCLASSIFICACAOFISCAL"));

						// "table_output" or
						// "output_table"--ITSITTRIBUTARIAFEDERAL

						NFEPRODUTO.ITSITTRIBUTARIAFEDERAL = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITSITTRIBUTARIAFEDERAL"));

						// "table_output" or "output_table"--ITQTDEPRODUTO

						NFEPRODUTO.ITQTDEPRODUTO = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITQTDEPRODUTO"));

						// "table_output" or "output_table"--ITUNIDADEMEDIDA

						NFEPRODUTO.ITUNIDADEMEDIDA = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITUNIDADEMEDIDA"));

						// "table_output" or
						// "output_table"--ITVALORPRECOUNITARIO

						NFEPRODUTO.ITVALORPRECOUNITARIO = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITVALORPRECOUNITARIO"));

						// "table_output" or "output_table"--ITVALORPRECOTOTAL

						NFEPRODUTO.ITVALORPRECOTOTAL = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITVALORPRECOTOTAL"));

						// "table_output" or "output_table"--ITVALORDESCONTO

						NFEPRODUTO.ITVALORDESCONTO = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITVALORDESCONTO"));

						// "table_output" or "output_table"--ITDESPESASFRETE

						NFEPRODUTO.ITDESPESASFRETE = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITDESPESASFRETE"));

						// "table_output" or "output_table"--ITDESPESASSEGURO

						NFEPRODUTO.ITDESPESASSEGURO = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITDESPESASSEGURO"));

						// "table_output" or "output_table"--ITOUTRASDESPESAS

						NFEPRODUTO.ITOUTRASDESPESAS = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITOUTRASDESPESAS"));

						// "table_output" or "output_table"--ITINDMOVIMENTACAO

						NFEPRODUTO.ITINDMOVIMENTACAO = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITINDMOVIMENTACAO"));

						// "table_output" or "output_table"--ITVALORBASEICMS

						NFEPRODUTO.ITVALORBASEICMS = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITVALORBASEICMS"));

						// "table_output" or "output_table"--ITALIQUOTAICMS

						NFEPRODUTO.ITALIQUOTAICMS = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITALIQUOTAICMS"));

						// "table_output" or "output_table"--ITVALORICMS

						NFEPRODUTO.ITVALORICMS = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITVALORICMS"));

						// "table_output" or "output_table"--ITVALORISENTOICM

						NFEPRODUTO.ITVALORISENTOICM = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITVALORISENTOICM"));

						// "table_output" or "output_table"--ITVALOROUTROICM

						NFEPRODUTO.ITVALOROUTROICM = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITVALOROUTROICM"));

						// "table_output" or "output_table"--ITVALORCONTABILICM

						NFEPRODUTO.ITVALORCONTABILICM = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITVALORCONTABILICM"));

						// "table_output" or "output_table"--FABASEPIS

						NFEPRODUTO.FABASEPIS = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FABASEPIS"));

						// "table_output" or "output_table"--ITBASEPIS

						NFEPRODUTO.ITBASEPIS = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITBASEPIS"));

						// "table_output" or "output_table"--ITALIQUOTAPIS

						NFEPRODUTO.ITALIQUOTAPIS = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITALIQUOTAPIS"));

						// "table_output" or "output_table"--ITVALORPIS

						NFEPRODUTO.ITVALORPIS = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITVALORPIS"));

						// "table_output" or "output_table"--FABASECOFINS

						NFEPRODUTO.FABASECOFINS = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FABASECOFINS"));

						// "table_output" or "output_table"--ITBASECOFINS

						NFEPRODUTO.ITBASECOFINS = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITBASECOFINS"));

						// "table_output" or "output_table"--ITALIQUOTACOFINS

						NFEPRODUTO.ITALIQUOTACOFINS = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITALIQUOTACOFINS"));

						// "table_output" or "output_table"--ITVALORCOFINS

						NFEPRODUTO.ITVALORCOFINS = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITVALORCOFINS"));

						// "table_output" or "output_table"--ITDESCRCOMPLEMENTAR

						NFEPRODUTO.ITDESCRCOMPLEMENTAR = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITDESCRCOMPLEMENTAR"));

						// "table_output" or "output_table"--ITPRECOTOTAL

						NFEPRODUTO.ITPRECOTOTAL = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITPRECOTOTAL"));

						// "table_output" or
						// "output_table"--ITSITUACAOTRIBUTARIAESTADUAL

						NFEPRODUTO.ITSITUACAOTRIBUTARIAESTADUAL = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITSITUACAOTRIBUTARIAESTADUAL"));

						// "table_output" or "output_table"--ITSITTRIBCOFINS

						NFEPRODUTO.ITSITTRIBCOFINS = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITSITTRIBCOFINS"));

						// "table_output" or "output_table"--ITSITTRIBPIS

						NFEPRODUTO.ITSITTRIBPIS = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITSITTRIBPIS"));

						// "table_output" or "output_table"--ITCONTAANALITICA

						NFEPRODUTO.ITCONTAANALITICA = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITCONTAANALITICA"));

						// "table_output" or "output_table"--FAINDMOVIMENTO

						NFEPRODUTO.FAINDMOVIMENTO = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("FAINDMOVIMENTO"));

						// "table_output" or "output_table"--ITUNIDADEPADRAO

						NFEPRODUTO.ITUNIDADEPADRAO = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITUNIDADEPADRAO"));

						// "table_output" or "output_table"--ITINDTRIBUTACAOIPI

						NFEPRODUTO.ITINDTRIBUTACAOIPI = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITINDTRIBUTACAOIPI"));

						// "table_output" or "output_table"--ITVALORCONTABILIPI

						NFEPRODUTO.ITVALORCONTABILIPI = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITVALORCONTABILIPI"));

						// "table_output" or
						// "output_table"--ITVALORTOTALDOCUMENTO

						NFEPRODUTO.ITVALORTOTALDOCUMENTO = ParserUtils
								.parseTo_String(table_NFEPRODUTO_tSAPInput_1
										.getString("ITVALORTOTALDOCUMENTO"));

						if (go_NFEPRODUTO) {
							table_NFEPRODUTO_tSAPInput_1.nextRow();
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
					// Start of branch "NFEPRODUTO"
					if (NFEPRODUTO != null) {

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
							outSMART_tmp.FACODIGOEMPRESA = NFEPRODUTO.FACODIGOEMPRESA;
							outSMART_tmp.FAEMITENTE = NFEPRODUTO.FAEMITENTE;
							outSMART_tmp.FAMODELODOCUMENTO = NFEPRODUTO.FAMODELODOCUMENTO;
							outSMART_tmp.FATIPODOCUMENTO = NFEPRODUTO.FATIPODOCUMENTO;
							outSMART_tmp.FASERIEDOCUMENTO = NFEPRODUTO.FASERIEDOCUMENTO;
							outSMART_tmp.FANUMERODOCUMENTO = NFEPRODUTO.FANUMERODOCUMENTO;
							outSMART_tmp.FANFECHAVE = NFEPRODUTO.FANFECHAVE;
							outSMART_tmp.FADATAEMISSAO = NFEPRODUTO.FADATAEMISSAO;
							outSMART_tmp.FADATASAIDA = NFEPRODUTO.FADATAEMISSAO;
							outSMART_tmp.FAHORASAIDA = NFEPRODUTO.FAHORASAIDA;
							outSMART_tmp.FACODIGOCLIENTE = NFEPRODUTO.FACODIGOCLIENTE;
							outSMART_tmp.FAUFCLIENTE = NFEPRODUTO.FAUFCLIENTE;
							outSMART_tmp.FASITUACAODOC = NFEPRODUTO.FASITUACAODOC;
							outSMART_tmp.FAINDCANCELAMENTO = NFEPRODUTO.FAINDCANCELAMENTO;
							outSMART_tmp.FACONSUMIDOR = NFEPRODUTO.FACONSUMIDOR;
							outSMART_tmp.FACONTRIBUINTE = NFEPRODUTO.FACONTRIBUINTE;
							outSMART_tmp.FAVALORPRODUTOS = NFEPRODUTO.FAVALORPRODUTOS;
							outSMART_tmp.FATIPOFATURA = NFEPRODUTO.FATIPOFATURA;
							outSMART_tmp.FATOTALNFISCAL = NFEPRODUTO.FATOTALNFISCAL;
							outSMART_tmp.FABASEICMS = NFEPRODUTO.FABASEICMS;
							outSMART_tmp.FATOTALICM = NFEPRODUTO.FATOTALICM;
							outSMART_tmp.FABASEREDUCAOIPI = NFEPRODUTO.FABASEREDUCAOIPI;
							outSMART_tmp.FAVALORTOTALIPI = NFEPRODUTO.FAVALORTOTALIPI;
							outSMART_tmp.FATOTALBASESUBST = NFEPRODUTO.FATOTALBASESUBST;
							outSMART_tmp.FATOTALICMSUBST = NFEPRODUTO.FATOTALICMSUBST;
							outSMART_tmp.FAINSCRICAOSUBSTITUTO = NFEPRODUTO.FAINSCRICAOSUBSTITUTO;
							outSMART_tmp.FAVALORFRETE = NFEPRODUTO.FAVALORFRETE;
							outSMART_tmp.FAVALORSEGURO = NFEPRODUTO.FAVALORSEGURO;
							outSMART_tmp.FAVALOROUTRAS = NFEPRODUTO.FAVALOROUTRAS;
							outSMART_tmp.FAVIATRANSPORTE = NFEPRODUTO.FAVIATRANSPORTE;
							outSMART_tmp.FAMODALIDADEFRETE = NFEPRODUTO.FAMODALIDADEFRETE;
							outSMART_tmp.FACODIGOTRANSPORTADOR = NFEPRODUTO.FACODIGOTRANSPORTADOR;
							outSMART_tmp.FAQTDEVOLUMES = NFEPRODUTO.FAQTDEVOLUMES;
							outSMART_tmp.FAESPECIEVOLUMES = NFEPRODUTO.FAESPECIEVOLUMES;
							outSMART_tmp.FAMARCAVOLUMES = NFEPRODUTO.FAMARCAVOLUMES;
							outSMART_tmp.FANUMERACAOVOLUMES = NFEPRODUTO.FANUMERACAOVOLUMES;
							outSMART_tmp.FAPESOBRUTO = NFEPRODUTO.FAPESOBRUTO;
							outSMART_tmp.FAPESOLIQUIDO = NFEPRODUTO.FAPESOLIQUIDO;
							outSMART_tmp.FAIDENTIFICACAOVEICULO = NFEPRODUTO.FAIDENTIFICACAOVEICULO;
							outSMART_tmp.FATIPONOTA = NFEPRODUTO.FATIPONOTA;
							outSMART_tmp.ITNUMEROITEM = NFEPRODUTO.ITNUMEROITEM;
							outSMART_tmp.ITCODIGOPRODUTO = NFEPRODUTO.ITCODIGOPRODUTO;
							outSMART_tmp.ITCFOP = NFEPRODUTO.ITCFOP;
							outSMART_tmp.ITCLASSIFICACAOFISCAL = NFEPRODUTO.ITCLASSIFICACAOFISCAL;
							outSMART_tmp.ITSITTRIBUTARIAFEDERAL = NFEPRODUTO.ITSITTRIBUTARIAFEDERAL;
							outSMART_tmp.ITQTDEPRODUTO = NFEPRODUTO.ITQTDEPRODUTO;
							outSMART_tmp.ITUNIDADEMEDIDA = NFEPRODUTO.ITUNIDADEMEDIDA;
							outSMART_tmp.ITVALORPRECOUNITARIO = NFEPRODUTO.ITVALORPRECOUNITARIO;
							outSMART_tmp.ITVALORPRECOTOTAL = NFEPRODUTO.ITVALORPRECOTOTAL;
							outSMART_tmp.ITVALORDESCONTO = NFEPRODUTO.ITVALORDESCONTO;
							outSMART_tmp.ITDESPESASFRETE = NFEPRODUTO.ITDESPESASFRETE;
							outSMART_tmp.ITDESPESASSEGURO = NFEPRODUTO.ITDESPESASSEGURO;
							outSMART_tmp.ITOUTRASDESPESAS = NFEPRODUTO.ITOUTRASDESPESAS;
							outSMART_tmp.ITINDMOVIMENTACAO = NFEPRODUTO.ITINDMOVIMENTACAO;
							outSMART_tmp.ITVALORBASEICMS = NFEPRODUTO.ITVALORBASEICMS;
							outSMART_tmp.ITALIQUOTAICMS = NFEPRODUTO.ITALIQUOTAICMS;
							outSMART_tmp.ITVALORICMS = NFEPRODUTO.ITVALORICMS;
							outSMART_tmp.ITVALORISENTOICM = NFEPRODUTO.ITVALORISENTOICM;
							outSMART_tmp.ITVALOROUTROICM = NFEPRODUTO.ITVALOROUTROICM;
							outSMART_tmp.ITVALORCONTABILICM = NFEPRODUTO.ITVALORCONTABILICM;
							outSMART_tmp.FABASEPIS = NFEPRODUTO.FABASEPIS;
							outSMART_tmp.ITBASEPIS = NFEPRODUTO.ITBASEPIS;
							outSMART_tmp.ITALIQUOTAPIS = NFEPRODUTO.ITALIQUOTAPIS;
							outSMART_tmp.ITVALORPIS = NFEPRODUTO.ITVALORPIS;
							outSMART_tmp.FABASECOFINS = NFEPRODUTO.FABASECOFINS;
							outSMART_tmp.ITBASECOFINS = NFEPRODUTO.ITBASECOFINS;
							outSMART_tmp.ITALIQUOTACOFINS = NFEPRODUTO.ITALIQUOTACOFINS;
							outSMART_tmp.ITVALORCOFINS = NFEPRODUTO.ITVALORCOFINS;
							outSMART_tmp.ITDESCRCOMPLEMENTAR = NFEPRODUTO.ITDESCRCOMPLEMENTAR;
							outSMART_tmp.ITPRECOTOTAL = NFEPRODUTO.ITPRECOTOTAL;
							outSMART_tmp.ITSITUACAOTRIBUTARIAESTADUAL = NFEPRODUTO.ITSITUACAOTRIBUTARIAESTADUAL;
							outSMART_tmp.ITSITTRIBCOFINS = NFEPRODUTO.ITSITTRIBCOFINS;
							outSMART_tmp.ITSITTRIBPIS = NFEPRODUTO.ITSITTRIBPIS;
							outSMART_tmp.ITCONTAANALITICA = NFEPRODUTO.ITCONTAANALITICA;
							outSMART_tmp.FAINDMOVIMENTO = NFEPRODUTO.FAINDMOVIMENTO;
							outSMART_tmp.ITUNIDADEPADRAO = NFEPRODUTO.ITUNIDADEPADRAO;
							outSMART_tmp.ITINDTRIBUTACAOIPI = NFEPRODUTO.ITINDTRIBUTACAOIPI;
							outSMART_tmp.ITVALORCONTABILIPI = NFEPRODUTO.ITVALORCONTABILIPI;
							outSMART_tmp.ITVALORTOTALDOCUMENTO = NFEPRODUTO.ITVALORTOTALDOCUMENTO;
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

							String searchStr_tReplace_1_1 = "." + "";
							row1.ITCLASSIFICACAOFISCAL = StringUtils
									.replaceAllStrictly(
											row1.ITCLASSIFICACAOFISCAL,
											searchStr_tReplace_1_1, "" + "",
											false, false);
							String searchStr_tReplace_1_2 = "00000" + "";
							row1.ITSITTRIBUTARIAFEDERAL = StringUtils
									.replaceAllStrictly(
											row1.ITSITTRIBUTARIAFEDERAL,
											searchStr_tReplace_1_2, "050" + "",
											false, false);
							String searchStr_tReplace_1_3 = "00001" + "";
							row1.ITSITTRIBUTARIAFEDERAL = StringUtils
									.replaceAllStrictly(
											row1.ITSITTRIBUTARIAFEDERAL,
											searchStr_tReplace_1_3, "051" + "",
											false, false);
							String searchStr_tReplace_1_4 = "00002" + "";
							row1.ITSITTRIBUTARIAFEDERAL = StringUtils
									.replaceAllStrictly(
											row1.ITSITTRIBUTARIAFEDERAL,
											searchStr_tReplace_1_4, "052" + "",
											false, false);
							String searchStr_tReplace_1_5 = "00003" + "";
							row1.ITSITTRIBUTARIAFEDERAL = StringUtils
									.replaceAllStrictly(
											row1.ITSITTRIBUTARIAFEDERAL,
											searchStr_tReplace_1_5, "053" + "",
											false, false);
							String searchStr_tReplace_1_6 = "00004" + "";
							row1.ITSITTRIBUTARIAFEDERAL = StringUtils
									.replaceAllStrictly(
											row1.ITSITTRIBUTARIAFEDERAL,
											searchStr_tReplace_1_6, "054" + "",
											false, false);
							String searchStr_tReplace_1_7 = "00005" + "";
							row1.ITSITTRIBUTARIAFEDERAL = StringUtils
									.replaceAllStrictly(
											row1.ITSITTRIBUTARIAFEDERAL,
											searchStr_tReplace_1_7, "055" + "",
											false, false);
							String searchStr_tReplace_1_8 = "00049" + "";
							row1.ITSITTRIBUTARIAFEDERAL = StringUtils
									.replaceAllStrictly(
											row1.ITSITTRIBUTARIAFEDERAL,
											searchStr_tReplace_1_8, "099" + "",
											false, false);
							String searchStr_tReplace_1_9 = "10002" + "";
							row1.ITSITTRIBUTARIAFEDERAL = StringUtils
									.replaceAllStrictly(
											row1.ITSITTRIBUTARIAFEDERAL,
											searchStr_tReplace_1_9, "000" + "",
											false, false);
							String searchStr_tReplace_1_10 = "99999" + "";
							row1.ITSITTRIBUTARIAFEDERAL = StringUtils
									.replaceAllStrictly(
											row1.ITSITTRIBUTARIAFEDERAL,
											searchStr_tReplace_1_10,
											"000" + "", false, false);
							String searchStr_tReplace_1_11 = "0001" + "";
							row1.FACODIGOEMPRESA = StringUtils
									.replaceAllStrictly(row1.FACODIGOEMPRESA,
											searchStr_tReplace_1_11,
											"001" + "", false, false);
							String searchStr_tReplace_1_12 = "0002" + "";
							row1.FACODIGOEMPRESA = StringUtils
									.replaceAllStrictly(row1.FACODIGOEMPRESA,
											searchStr_tReplace_1_12,
											"002" + "", false, false);
							row2.FACODIGOEMPRESA = row1.FACODIGOEMPRESA;

							row2.FAEMITENTE = row1.FAEMITENTE;

							row2.FAMODELODOCUMENTO = row1.FAMODELODOCUMENTO;

							row2.FATIPODOCUMENTO = row1.FATIPODOCUMENTO;

							row2.FASERIEDOCUMENTO = row1.FASERIEDOCUMENTO;

							row2.FANUMERODOCUMENTO = row1.FANUMERODOCUMENTO;

							row2.FANFECHAVE = row1.FANFECHAVE;

							row2.FADATAEMISSAO = row1.FADATAEMISSAO;

							row2.FADATASAIDA = row1.FADATASAIDA;

							row2.FAHORASAIDA = row1.FAHORASAIDA;

							row2.FACODIGOCLIENTE = row1.FACODIGOCLIENTE;

							row2.FAUFCLIENTE = row1.FAUFCLIENTE;

							row2.FASITUACAODOC = row1.FASITUACAODOC;

							row2.FAINDCANCELAMENTO = row1.FAINDCANCELAMENTO;

							row2.FACONSUMIDOR = row1.FACONSUMIDOR;

							row2.FACONTRIBUINTE = row1.FACONTRIBUINTE;

							row2.FAVALORPRODUTOS = row1.FAVALORPRODUTOS;

							row2.FATIPOFATURA = row1.FATIPOFATURA;

							row2.FATOTALNFISCAL = row1.FATOTALNFISCAL;

							row2.FABASEICMS = row1.FABASEICMS;

							row2.FATOTALICM = row1.FATOTALICM;

							row2.FABASEREDUCAOIPI = row1.FABASEREDUCAOIPI;

							row2.FAVALORTOTALIPI = row1.FAVALORTOTALIPI;

							row2.FATOTALBASESUBST = row1.FATOTALBASESUBST;

							row2.FATOTALICMSUBST = row1.FATOTALICMSUBST;

							row2.FAINSCRICAOSUBSTITUTO = row1.FAINSCRICAOSUBSTITUTO;

							row2.FAVALORFRETE = row1.FAVALORFRETE;

							row2.FAVALORSEGURO = row1.FAVALORSEGURO;

							row2.FAVALOROUTRAS = row1.FAVALOROUTRAS;

							row2.FAVIATRANSPORTE = row1.FAVIATRANSPORTE;

							row2.FAMODALIDADEFRETE = row1.FAMODALIDADEFRETE;

							row2.FACODIGOTRANSPORTADOR = row1.FACODIGOTRANSPORTADOR;

							row2.FAQTDEVOLUMES = row1.FAQTDEVOLUMES;

							row2.FAESPECIEVOLUMES = row1.FAESPECIEVOLUMES;

							row2.FAMARCAVOLUMES = row1.FAMARCAVOLUMES;

							row2.FANUMERACAOVOLUMES = row1.FANUMERACAOVOLUMES;

							row2.FAPESOBRUTO = row1.FAPESOBRUTO;

							row2.FAPESOLIQUIDO = row1.FAPESOLIQUIDO;

							row2.FAIDENTIFICACAOVEICULO = row1.FAIDENTIFICACAOVEICULO;

							row2.FATIPONOTA = row1.FATIPONOTA;

							row2.ITNUMEROITEM = row1.ITNUMEROITEM;

							row2.ITCODIGOPRODUTO = row1.ITCODIGOPRODUTO;

							row2.ITCFOP = row1.ITCFOP;

							row2.ITCLASSIFICACAOFISCAL = row1.ITCLASSIFICACAOFISCAL;

							row2.ITSITTRIBUTARIAFEDERAL = row1.ITSITTRIBUTARIAFEDERAL;

							row2.ITQTDEPRODUTO = row1.ITQTDEPRODUTO;

							row2.ITUNIDADEMEDIDA = row1.ITUNIDADEMEDIDA;

							row2.ITVALORPRECOUNITARIO = row1.ITVALORPRECOUNITARIO;

							row2.ITVALORPRECOTOTAL = row1.ITVALORPRECOTOTAL;

							row2.ITVALORDESCONTO = row1.ITVALORDESCONTO;

							row2.ITDESPESASFRETE = row1.ITDESPESASFRETE;

							row2.ITDESPESASSEGURO = row1.ITDESPESASSEGURO;

							row2.ITOUTRASDESPESAS = row1.ITOUTRASDESPESAS;

							row2.ITINDMOVIMENTACAO = row1.ITINDMOVIMENTACAO;

							row2.ITVALORBASEICMS = row1.ITVALORBASEICMS;

							row2.ITALIQUOTAICMS = row1.ITALIQUOTAICMS;

							row2.ITVALORICMS = row1.ITVALORICMS;

							row2.ITVALORISENTOICM = row1.ITVALORISENTOICM;

							row2.ITVALOROUTROICM = row1.ITVALOROUTROICM;

							row2.ITVALORCONTABILICM = row1.ITVALORCONTABILICM;

							row2.FABASEPIS = row1.FABASEPIS;

							row2.ITBASEPIS = row1.ITBASEPIS;

							row2.ITALIQUOTAPIS = row1.ITALIQUOTAPIS;

							row2.ITVALORPIS = row1.ITVALORPIS;

							row2.FABASECOFINS = row1.FABASECOFINS;

							row2.ITBASECOFINS = row1.ITBASECOFINS;

							row2.ITALIQUOTACOFINS = row1.ITALIQUOTACOFINS;

							row2.ITVALORCOFINS = row1.ITVALORCOFINS;

							row2.ITDESCRCOMPLEMENTAR = row1.ITDESCRCOMPLEMENTAR;

							row2.ITPRECOTOTAL = row1.ITPRECOTOTAL;

							row2.ITSITUACAOTRIBUTARIAESTADUAL = row1.ITSITUACAOTRIBUTARIAESTADUAL;

							row2.ITSITTRIBCOFINS = row1.ITSITTRIBCOFINS;

							row2.ITSITTRIBPIS = row1.ITSITTRIBPIS;

							row2.ITCONTAANALITICA = row1.ITCONTAANALITICA;

							row2.FAINDMOVIMENTO = row1.FAINDMOVIMENTO;

							row2.ITUNIDADEPADRAO = row1.ITUNIDADEPADRAO;

							row2.ITINDTRIBUTACAOIPI = row1.ITINDTRIBUTACAOIPI;

							row2.ITVALORCONTABILIPI = row1.ITVALORCONTABILIPI;

							row2.ITVALORTOTALDOCUMENTO = row1.ITVALORTOTALDOCUMENTO;

							nb_line_tReplace_1++;

							tos_count_tReplace_1++;

							/**
							 * [tReplace_1 main ] stop
							 */

							/**
							 * [tLogRow_1 main ] start
							 */

							currentComponent = "tLogRow_1";

							// /////////////////////

							String[] row_tLogRow_1 = new String[79];

							if (row2.FACODIGOEMPRESA != null) { //
								row_tLogRow_1[0] = String
										.valueOf(row2.FACODIGOEMPRESA);

							} //

							if (row2.FAEMITENTE != null) { //
								row_tLogRow_1[1] = String
										.valueOf(row2.FAEMITENTE);

							} //

							if (row2.FAMODELODOCUMENTO != null) { //
								row_tLogRow_1[2] = String
										.valueOf(row2.FAMODELODOCUMENTO);

							} //

							if (row2.FATIPODOCUMENTO != null) { //
								row_tLogRow_1[3] = String
										.valueOf(row2.FATIPODOCUMENTO);

							} //

							if (row2.FASERIEDOCUMENTO != null) { //
								row_tLogRow_1[4] = String
										.valueOf(row2.FASERIEDOCUMENTO);

							} //

							if (row2.FANUMERODOCUMENTO != null) { //
								row_tLogRow_1[5] = String
										.valueOf(row2.FANUMERODOCUMENTO);

							} //

							if (row2.FANFECHAVE != null) { //
								row_tLogRow_1[6] = String
										.valueOf(row2.FANFECHAVE);

							} //

							if (row2.FADATAEMISSAO != null) { //
								row_tLogRow_1[7] = FormatterUtils.format_Date(
										row2.FADATAEMISSAO, "yy-MM-dd");

							} //

							if (row2.FADATASAIDA != null) { //
								row_tLogRow_1[8] = FormatterUtils.format_Date(
										row2.FADATASAIDA, "yy-MM-dd");

							} //

							if (row2.FAHORASAIDA != null) { //
								row_tLogRow_1[9] = String
										.valueOf(row2.FAHORASAIDA);

							} //

							if (row2.FACODIGOCLIENTE != null) { //
								row_tLogRow_1[10] = String
										.valueOf(row2.FACODIGOCLIENTE);

							} //

							if (row2.FAUFCLIENTE != null) { //
								row_tLogRow_1[11] = String
										.valueOf(row2.FAUFCLIENTE);

							} //

							if (row2.FASITUACAODOC != null) { //
								row_tLogRow_1[12] = String
										.valueOf(row2.FASITUACAODOC);

							} //

							if (row2.FAINDCANCELAMENTO != null) { //
								row_tLogRow_1[13] = String
										.valueOf(row2.FAINDCANCELAMENTO);

							} //

							if (row2.FACONSUMIDOR != null) { //
								row_tLogRow_1[14] = String
										.valueOf(row2.FACONSUMIDOR);

							} //

							if (row2.FACONTRIBUINTE != null) { //
								row_tLogRow_1[15] = String
										.valueOf(row2.FACONTRIBUINTE);

							} //

							if (row2.FAVALORPRODUTOS != null) { //
								row_tLogRow_1[16] = String
										.valueOf(row2.FAVALORPRODUTOS);

							} //

							if (row2.FATIPOFATURA != null) { //
								row_tLogRow_1[17] = String
										.valueOf(row2.FATIPOFATURA);

							} //

							if (row2.FATOTALNFISCAL != null) { //
								row_tLogRow_1[18] = String
										.valueOf(row2.FATOTALNFISCAL);

							} //

							if (row2.FABASEICMS != null) { //
								row_tLogRow_1[19] = String
										.valueOf(row2.FABASEICMS);

							} //

							if (row2.FATOTALICM != null) { //
								row_tLogRow_1[20] = String
										.valueOf(row2.FATOTALICM);

							} //

							if (row2.FABASEREDUCAOIPI != null) { //
								row_tLogRow_1[21] = String
										.valueOf(row2.FABASEREDUCAOIPI);

							} //

							if (row2.FAVALORTOTALIPI != null) { //
								row_tLogRow_1[22] = String
										.valueOf(row2.FAVALORTOTALIPI);

							} //

							if (row2.FATOTALBASESUBST != null) { //
								row_tLogRow_1[23] = String
										.valueOf(row2.FATOTALBASESUBST);

							} //

							if (row2.FATOTALICMSUBST != null) { //
								row_tLogRow_1[24] = String
										.valueOf(row2.FATOTALICMSUBST);

							} //

							if (row2.FAINSCRICAOSUBSTITUTO != null) { //
								row_tLogRow_1[25] = String
										.valueOf(row2.FAINSCRICAOSUBSTITUTO);

							} //

							if (row2.FAVALORFRETE != null) { //
								row_tLogRow_1[26] = String
										.valueOf(row2.FAVALORFRETE);

							} //

							if (row2.FAVALORSEGURO != null) { //
								row_tLogRow_1[27] = String
										.valueOf(row2.FAVALORSEGURO);

							} //

							if (row2.FAVALOROUTRAS != null) { //
								row_tLogRow_1[28] = String
										.valueOf(row2.FAVALOROUTRAS);

							} //

							if (row2.FAVIATRANSPORTE != null) { //
								row_tLogRow_1[29] = String
										.valueOf(row2.FAVIATRANSPORTE);

							} //

							if (row2.FAMODALIDADEFRETE != null) { //
								row_tLogRow_1[30] = String
										.valueOf(row2.FAMODALIDADEFRETE);

							} //

							if (row2.FACODIGOTRANSPORTADOR != null) { //
								row_tLogRow_1[31] = String
										.valueOf(row2.FACODIGOTRANSPORTADOR);

							} //

							if (row2.FAQTDEVOLUMES != null) { //
								row_tLogRow_1[32] = String
										.valueOf(row2.FAQTDEVOLUMES);

							} //

							if (row2.FAESPECIEVOLUMES != null) { //
								row_tLogRow_1[33] = String
										.valueOf(row2.FAESPECIEVOLUMES);

							} //

							if (row2.FAMARCAVOLUMES != null) { //
								row_tLogRow_1[34] = String
										.valueOf(row2.FAMARCAVOLUMES);

							} //

							if (row2.FANUMERACAOVOLUMES != null) { //
								row_tLogRow_1[35] = String
										.valueOf(row2.FANUMERACAOVOLUMES);

							} //

							if (row2.FAPESOBRUTO != null) { //
								row_tLogRow_1[36] = String
										.valueOf(row2.FAPESOBRUTO);

							} //

							if (row2.FAPESOLIQUIDO != null) { //
								row_tLogRow_1[37] = String
										.valueOf(row2.FAPESOLIQUIDO);

							} //

							if (row2.FAIDENTIFICACAOVEICULO != null) { //
								row_tLogRow_1[38] = String
										.valueOf(row2.FAIDENTIFICACAOVEICULO);

							} //

							if (row2.FATIPONOTA != null) { //
								row_tLogRow_1[39] = String
										.valueOf(row2.FATIPONOTA);

							} //

							if (row2.ITNUMEROITEM != null) { //
								row_tLogRow_1[40] = String
										.valueOf(row2.ITNUMEROITEM);

							} //

							if (row2.ITCODIGOPRODUTO != null) { //
								row_tLogRow_1[41] = String
										.valueOf(row2.ITCODIGOPRODUTO);

							} //

							if (row2.ITCFOP != null) { //
								row_tLogRow_1[42] = String.valueOf(row2.ITCFOP);

							} //

							if (row2.ITCLASSIFICACAOFISCAL != null) { //
								row_tLogRow_1[43] = String
										.valueOf(row2.ITCLASSIFICACAOFISCAL);

							} //

							if (row2.ITSITTRIBUTARIAFEDERAL != null) { //
								row_tLogRow_1[44] = String
										.valueOf(row2.ITSITTRIBUTARIAFEDERAL);

							} //

							if (row2.ITQTDEPRODUTO != null) { //
								row_tLogRow_1[45] = String
										.valueOf(row2.ITQTDEPRODUTO);

							} //

							if (row2.ITUNIDADEMEDIDA != null) { //
								row_tLogRow_1[46] = String
										.valueOf(row2.ITUNIDADEMEDIDA);

							} //

							if (row2.ITVALORPRECOUNITARIO != null) { //
								row_tLogRow_1[47] = String
										.valueOf(row2.ITVALORPRECOUNITARIO);

							} //

							if (row2.ITVALORPRECOTOTAL != null) { //
								row_tLogRow_1[48] = String
										.valueOf(row2.ITVALORPRECOTOTAL);

							} //

							if (row2.ITVALORDESCONTO != null) { //
								row_tLogRow_1[49] = String
										.valueOf(row2.ITVALORDESCONTO);

							} //

							if (row2.ITDESPESASFRETE != null) { //
								row_tLogRow_1[50] = String
										.valueOf(row2.ITDESPESASFRETE);

							} //

							if (row2.ITDESPESASSEGURO != null) { //
								row_tLogRow_1[51] = String
										.valueOf(row2.ITDESPESASSEGURO);

							} //

							if (row2.ITOUTRASDESPESAS != null) { //
								row_tLogRow_1[52] = String
										.valueOf(row2.ITOUTRASDESPESAS);

							} //

							if (row2.ITINDMOVIMENTACAO != null) { //
								row_tLogRow_1[53] = String
										.valueOf(row2.ITINDMOVIMENTACAO);

							} //

							if (row2.ITVALORBASEICMS != null) { //
								row_tLogRow_1[54] = String
										.valueOf(row2.ITVALORBASEICMS);

							} //

							if (row2.ITALIQUOTAICMS != null) { //
								row_tLogRow_1[55] = String
										.valueOf(row2.ITALIQUOTAICMS);

							} //

							if (row2.ITVALORICMS != null) { //
								row_tLogRow_1[56] = String
										.valueOf(row2.ITVALORICMS);

							} //

							if (row2.ITVALORISENTOICM != null) { //
								row_tLogRow_1[57] = String
										.valueOf(row2.ITVALORISENTOICM);

							} //

							if (row2.ITVALOROUTROICM != null) { //
								row_tLogRow_1[58] = String
										.valueOf(row2.ITVALOROUTROICM);

							} //

							if (row2.ITVALORCONTABILICM != null) { //
								row_tLogRow_1[59] = String
										.valueOf(row2.ITVALORCONTABILICM);

							} //

							if (row2.FABASEPIS != null) { //
								row_tLogRow_1[60] = String
										.valueOf(row2.FABASEPIS);

							} //

							if (row2.ITBASEPIS != null) { //
								row_tLogRow_1[61] = String
										.valueOf(row2.ITBASEPIS);

							} //

							if (row2.ITALIQUOTAPIS != null) { //
								row_tLogRow_1[62] = String
										.valueOf(row2.ITALIQUOTAPIS);

							} //

							if (row2.ITVALORPIS != null) { //
								row_tLogRow_1[63] = String
										.valueOf(row2.ITVALORPIS);

							} //

							if (row2.FABASECOFINS != null) { //
								row_tLogRow_1[64] = String
										.valueOf(row2.FABASECOFINS);

							} //

							if (row2.ITBASECOFINS != null) { //
								row_tLogRow_1[65] = String
										.valueOf(row2.ITBASECOFINS);

							} //

							if (row2.ITALIQUOTACOFINS != null) { //
								row_tLogRow_1[66] = String
										.valueOf(row2.ITALIQUOTACOFINS);

							} //

							if (row2.ITVALORCOFINS != null) { //
								row_tLogRow_1[67] = String
										.valueOf(row2.ITVALORCOFINS);

							} //

							if (row2.ITDESCRCOMPLEMENTAR != null) { //
								row_tLogRow_1[68] = String
										.valueOf(row2.ITDESCRCOMPLEMENTAR);

							} //

							if (row2.ITPRECOTOTAL != null) { //
								row_tLogRow_1[69] = String
										.valueOf(row2.ITPRECOTOTAL);

							} //

							if (row2.ITSITUACAOTRIBUTARIAESTADUAL != null) { //
								row_tLogRow_1[70] = String
										.valueOf(row2.ITSITUACAOTRIBUTARIAESTADUAL);

							} //

							if (row2.ITSITTRIBCOFINS != null) { //
								row_tLogRow_1[71] = String
										.valueOf(row2.ITSITTRIBCOFINS);

							} //

							if (row2.ITSITTRIBPIS != null) { //
								row_tLogRow_1[72] = String
										.valueOf(row2.ITSITTRIBPIS);

							} //

							if (row2.ITCONTAANALITICA != null) { //
								row_tLogRow_1[73] = String
										.valueOf(row2.ITCONTAANALITICA);

							} //

							if (row2.FAINDMOVIMENTO != null) { //
								row_tLogRow_1[74] = String
										.valueOf(row2.FAINDMOVIMENTO);

							} //

							if (row2.ITUNIDADEPADRAO != null) { //
								row_tLogRow_1[75] = String
										.valueOf(row2.ITUNIDADEPADRAO);

							} //

							if (row2.ITINDTRIBUTACAOIPI != null) { //
								row_tLogRow_1[76] = String
										.valueOf(row2.ITINDTRIBUTACAOIPI);

							} //

							if (row2.ITVALORCONTABILIPI != null) { //
								row_tLogRow_1[77] = String
										.valueOf(row2.ITVALORCONTABILIPI);

							} //

							if (row2.ITVALORTOTALDOCUMENTO != null) { //
								row_tLogRow_1[78] = String
										.valueOf(row2.ITVALORTOTALDOCUMENTO);

							} //

							util_tLogRow_1.addRow(row_tLogRow_1);
							nb_line_tLogRow_1++;
							// ////

							// ////

							// /////////////////////

							row3 = row2;

							tos_count_tLogRow_1++;

							/**
							 * [tLogRow_1 main ] stop
							 */

							/**
							 * [tMSSqlOutput_1 main ] start
							 */

							currentComponent = "tMSSqlOutput_1";

							whetherReject_tMSSqlOutput_1 = false;
							if (row3.FACODIGOEMPRESA == null) {
								pstmt_tMSSqlOutput_1.setNull(1,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(1,
										row3.FACODIGOEMPRESA);
							}

							if (row3.FAEMITENTE == null) {
								pstmt_tMSSqlOutput_1.setNull(2,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(2,
										row3.FAEMITENTE);
							}

							if (row3.FAMODELODOCUMENTO == null) {
								pstmt_tMSSqlOutput_1.setNull(3,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(3,
										row3.FAMODELODOCUMENTO);
							}

							if (row3.FATIPODOCUMENTO == null) {
								pstmt_tMSSqlOutput_1.setNull(4,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(4,
										row3.FATIPODOCUMENTO);
							}

							if (row3.FASERIEDOCUMENTO == null) {
								pstmt_tMSSqlOutput_1.setNull(5,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(5,
										row3.FASERIEDOCUMENTO);
							}

							if (row3.FANUMERODOCUMENTO == null) {
								pstmt_tMSSqlOutput_1.setNull(6,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(6,
										row3.FANUMERODOCUMENTO);
							}

							if (row3.FANFECHAVE == null) {
								pstmt_tMSSqlOutput_1.setNull(7,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(7,
										row3.FANFECHAVE);
							}

							if (row3.FADATAEMISSAO != null) {
								if (row3.FADATAEMISSAO != null
										&& row3.FADATAEMISSAO.getTime() < year2_tMSSqlOutput_1) {
									pstmt_tMSSqlOutput_1.setString(8,
											TalendDate.formatDate("dd/MM/yyyy",
													row3.FADATAEMISSAO));
								} else {
									pstmt_tMSSqlOutput_1.setTimestamp(
											8,
											new java.sql.Timestamp(
													row3.FADATAEMISSAO
															.getTime()));
								}
							} else {
								pstmt_tMSSqlOutput_1.setNull(8,
										java.sql.Types.DATE);
							}

							if (row3.FADATASAIDA != null) {
								if (row3.FADATASAIDA != null
										&& row3.FADATASAIDA.getTime() < year2_tMSSqlOutput_1) {
									pstmt_tMSSqlOutput_1.setString(9,
											TalendDate.formatDate("dd/MM/yyyy",
													row3.FADATASAIDA));
								} else {
									pstmt_tMSSqlOutput_1
											.setTimestamp(
													9,
													new java.sql.Timestamp(
															row3.FADATASAIDA
																	.getTime()));
								}
							} else {
								pstmt_tMSSqlOutput_1.setNull(9,
										java.sql.Types.DATE);
							}

							if (row3.FAHORASAIDA == null) {
								pstmt_tMSSqlOutput_1.setNull(10,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(10,
										row3.FAHORASAIDA);
							}

							if (row3.FACODIGOCLIENTE == null) {
								pstmt_tMSSqlOutput_1.setNull(11,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(11,
										row3.FACODIGOCLIENTE);
							}

							if (row3.FAUFCLIENTE == null) {
								pstmt_tMSSqlOutput_1.setNull(12,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(12,
										row3.FAUFCLIENTE);
							}

							if (row3.FASITUACAODOC == null) {
								pstmt_tMSSqlOutput_1.setNull(13,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(13,
										row3.FASITUACAODOC);
							}

							if (row3.FAINDCANCELAMENTO == null) {
								pstmt_tMSSqlOutput_1.setNull(14,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(14,
										row3.FAINDCANCELAMENTO);
							}

							if (row3.FACONSUMIDOR == null) {
								pstmt_tMSSqlOutput_1.setNull(15,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(15,
										row3.FACONSUMIDOR);
							}

							if (row3.FACONTRIBUINTE == null) {
								pstmt_tMSSqlOutput_1.setNull(16,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(16,
										row3.FACONTRIBUINTE);
							}

							if (row3.FAVALORPRODUTOS == null) {
								pstmt_tMSSqlOutput_1.setNull(17,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(17,
										row3.FAVALORPRODUTOS);
							}

							if (row3.FATIPOFATURA == null) {
								pstmt_tMSSqlOutput_1.setNull(18,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(18,
										row3.FATIPOFATURA);
							}

							if (row3.FATOTALNFISCAL == null) {
								pstmt_tMSSqlOutput_1.setNull(19,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(19,
										row3.FATOTALNFISCAL);
							}

							if (row3.FABASEICMS == null) {
								pstmt_tMSSqlOutput_1.setNull(20,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(20,
										row3.FABASEICMS);
							}

							if (row3.FATOTALICM == null) {
								pstmt_tMSSqlOutput_1.setNull(21,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(21,
										row3.FATOTALICM);
							}

							if (row3.FABASEREDUCAOIPI == null) {
								pstmt_tMSSqlOutput_1.setNull(22,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(22,
										row3.FABASEREDUCAOIPI);
							}

							if (row3.FAVALORTOTALIPI == null) {
								pstmt_tMSSqlOutput_1.setNull(23,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(23,
										row3.FAVALORTOTALIPI);
							}

							if (row3.FATOTALBASESUBST == null) {
								pstmt_tMSSqlOutput_1.setNull(24,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(24,
										row3.FATOTALBASESUBST);
							}

							if (row3.FATOTALICMSUBST == null) {
								pstmt_tMSSqlOutput_1.setNull(25,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(25,
										row3.FATOTALICMSUBST);
							}

							if (row3.FAINSCRICAOSUBSTITUTO == null) {
								pstmt_tMSSqlOutput_1.setNull(26,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(26,
										row3.FAINSCRICAOSUBSTITUTO);
							}

							if (row3.FAVALORFRETE == null) {
								pstmt_tMSSqlOutput_1.setNull(27,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(27,
										row3.FAVALORFRETE);
							}

							if (row3.FAVALORSEGURO == null) {
								pstmt_tMSSqlOutput_1.setNull(28,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(28,
										row3.FAVALORSEGURO);
							}

							if (row3.FAVALOROUTRAS == null) {
								pstmt_tMSSqlOutput_1.setNull(29,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(29,
										row3.FAVALOROUTRAS);
							}

							if (row3.FAVIATRANSPORTE == null) {
								pstmt_tMSSqlOutput_1.setNull(30,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(30,
										row3.FAVIATRANSPORTE);
							}

							if (row3.FAMODALIDADEFRETE == null) {
								pstmt_tMSSqlOutput_1.setNull(31,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(31,
										row3.FAMODALIDADEFRETE);
							}

							if (row3.FACODIGOTRANSPORTADOR == null) {
								pstmt_tMSSqlOutput_1.setNull(32,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(32,
										row3.FACODIGOTRANSPORTADOR);
							}

							if (row3.FAQTDEVOLUMES == null) {
								pstmt_tMSSqlOutput_1.setNull(33,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(33,
										row3.FAQTDEVOLUMES);
							}

							if (row3.FAESPECIEVOLUMES == null) {
								pstmt_tMSSqlOutput_1.setNull(34,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(34,
										row3.FAESPECIEVOLUMES);
							}

							if (row3.FAMARCAVOLUMES == null) {
								pstmt_tMSSqlOutput_1.setNull(35,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(35,
										row3.FAMARCAVOLUMES);
							}

							if (row3.FANUMERACAOVOLUMES == null) {
								pstmt_tMSSqlOutput_1.setNull(36,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(36,
										row3.FANUMERACAOVOLUMES);
							}

							if (row3.FAPESOBRUTO == null) {
								pstmt_tMSSqlOutput_1.setNull(37,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(37,
										row3.FAPESOBRUTO);
							}

							if (row3.FAPESOLIQUIDO == null) {
								pstmt_tMSSqlOutput_1.setNull(38,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(38,
										row3.FAPESOLIQUIDO);
							}

							if (row3.FAIDENTIFICACAOVEICULO == null) {
								pstmt_tMSSqlOutput_1.setNull(39,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(39,
										row3.FAIDENTIFICACAOVEICULO);
							}

							if (row3.FATIPONOTA == null) {
								pstmt_tMSSqlOutput_1.setNull(40,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(40,
										row3.FATIPONOTA);
							}

							if (row3.ITNUMEROITEM == null) {
								pstmt_tMSSqlOutput_1.setNull(41,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(41,
										row3.ITNUMEROITEM);
							}

							if (row3.ITCODIGOPRODUTO == null) {
								pstmt_tMSSqlOutput_1.setNull(42,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(42,
										row3.ITCODIGOPRODUTO);
							}

							if (row3.ITCFOP == null) {
								pstmt_tMSSqlOutput_1.setNull(43,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(43, row3.ITCFOP);
							}

							if (row3.ITCLASSIFICACAOFISCAL == null) {
								pstmt_tMSSqlOutput_1.setNull(44,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(44,
										row3.ITCLASSIFICACAOFISCAL);
							}

							if (row3.ITSITTRIBUTARIAFEDERAL == null) {
								pstmt_tMSSqlOutput_1.setNull(45,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(45,
										row3.ITSITTRIBUTARIAFEDERAL);
							}

							if (row3.ITQTDEPRODUTO == null) {
								pstmt_tMSSqlOutput_1.setNull(46,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(46,
										row3.ITQTDEPRODUTO);
							}

							if (row3.ITUNIDADEMEDIDA == null) {
								pstmt_tMSSqlOutput_1.setNull(47,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(47,
										row3.ITUNIDADEMEDIDA);
							}

							if (row3.ITVALORPRECOUNITARIO == null) {
								pstmt_tMSSqlOutput_1.setNull(48,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(48,
										row3.ITVALORPRECOUNITARIO);
							}

							if (row3.ITVALORPRECOTOTAL == null) {
								pstmt_tMSSqlOutput_1.setNull(49,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(49,
										row3.ITVALORPRECOTOTAL);
							}

							if (row3.ITVALORDESCONTO == null) {
								pstmt_tMSSqlOutput_1.setNull(50,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(50,
										row3.ITVALORDESCONTO);
							}

							if (row3.ITDESPESASFRETE == null) {
								pstmt_tMSSqlOutput_1.setNull(51,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(51,
										row3.ITDESPESASFRETE);
							}

							if (row3.ITDESPESASSEGURO == null) {
								pstmt_tMSSqlOutput_1.setNull(52,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(52,
										row3.ITDESPESASSEGURO);
							}

							if (row3.ITOUTRASDESPESAS == null) {
								pstmt_tMSSqlOutput_1.setNull(53,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(53,
										row3.ITOUTRASDESPESAS);
							}

							if (row3.ITINDMOVIMENTACAO == null) {
								pstmt_tMSSqlOutput_1.setNull(54,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(54,
										row3.ITINDMOVIMENTACAO);
							}

							if (row3.ITVALORBASEICMS == null) {
								pstmt_tMSSqlOutput_1.setNull(55,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(55,
										row3.ITVALORBASEICMS);
							}

							if (row3.ITALIQUOTAICMS == null) {
								pstmt_tMSSqlOutput_1.setNull(56,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(56,
										row3.ITALIQUOTAICMS);
							}

							if (row3.ITVALORICMS == null) {
								pstmt_tMSSqlOutput_1.setNull(57,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(57,
										row3.ITVALORICMS);
							}

							if (row3.ITVALORISENTOICM == null) {
								pstmt_tMSSqlOutput_1.setNull(58,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(58,
										row3.ITVALORISENTOICM);
							}

							if (row3.ITVALOROUTROICM == null) {
								pstmt_tMSSqlOutput_1.setNull(59,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(59,
										row3.ITVALOROUTROICM);
							}

							if (row3.ITVALORCONTABILICM == null) {
								pstmt_tMSSqlOutput_1.setNull(60,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(60,
										row3.ITVALORCONTABILICM);
							}

							if (row3.FABASEPIS == null) {
								pstmt_tMSSqlOutput_1.setNull(61,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(61,
										row3.FABASEPIS);
							}

							if (row3.ITBASEPIS == null) {
								pstmt_tMSSqlOutput_1.setNull(62,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(62,
										row3.ITBASEPIS);
							}

							if (row3.ITALIQUOTAPIS == null) {
								pstmt_tMSSqlOutput_1.setNull(63,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(63,
										row3.ITALIQUOTAPIS);
							}

							if (row3.ITVALORPIS == null) {
								pstmt_tMSSqlOutput_1.setNull(64,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(64,
										row3.ITVALORPIS);
							}

							if (row3.FABASECOFINS == null) {
								pstmt_tMSSqlOutput_1.setNull(65,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(65,
										row3.FABASECOFINS);
							}

							if (row3.ITBASECOFINS == null) {
								pstmt_tMSSqlOutput_1.setNull(66,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(66,
										row3.ITBASECOFINS);
							}

							if (row3.ITALIQUOTACOFINS == null) {
								pstmt_tMSSqlOutput_1.setNull(67,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(67,
										row3.ITALIQUOTACOFINS);
							}

							if (row3.ITVALORCOFINS == null) {
								pstmt_tMSSqlOutput_1.setNull(68,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(68,
										row3.ITVALORCOFINS);
							}

							if (row3.ITDESCRCOMPLEMENTAR == null) {
								pstmt_tMSSqlOutput_1.setNull(69,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(69,
										row3.ITDESCRCOMPLEMENTAR);
							}

							if (row3.ITPRECOTOTAL == null) {
								pstmt_tMSSqlOutput_1.setNull(70,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(70,
										row3.ITPRECOTOTAL);
							}

							if (row3.ITSITUACAOTRIBUTARIAESTADUAL == null) {
								pstmt_tMSSqlOutput_1.setNull(71,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(71,
										row3.ITSITUACAOTRIBUTARIAESTADUAL);
							}

							if (row3.ITSITTRIBCOFINS == null) {
								pstmt_tMSSqlOutput_1.setNull(72,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(72,
										row3.ITSITTRIBCOFINS);
							}

							if (row3.ITSITTRIBPIS == null) {
								pstmt_tMSSqlOutput_1.setNull(73,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(73,
										row3.ITSITTRIBPIS);
							}

							if (row3.ITCONTAANALITICA == null) {
								pstmt_tMSSqlOutput_1.setNull(74,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(74,
										row3.ITCONTAANALITICA);
							}

							if (row3.FAINDMOVIMENTO == null) {
								pstmt_tMSSqlOutput_1.setNull(75,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(75,
										row3.FAINDMOVIMENTO);
							}

							if (row3.ITUNIDADEPADRAO == null) {
								pstmt_tMSSqlOutput_1.setNull(76,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(76,
										row3.ITUNIDADEPADRAO);
							}

							if (row3.ITINDTRIBUTACAOIPI == null) {
								pstmt_tMSSqlOutput_1.setNull(77,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(77,
										row3.ITINDTRIBUTACAOIPI);
							}

							if (row3.ITVALORCONTABILIPI == null) {
								pstmt_tMSSqlOutput_1.setNull(78,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(78,
										row3.ITVALORCONTABILIPI);
							}

							if (row3.ITVALORTOTALDOCUMENTO == null) {
								pstmt_tMSSqlOutput_1.setNull(79,
										java.sql.Types.VARCHAR);
							} else {
								pstmt_tMSSqlOutput_1.setString(79,
										row3.ITVALORTOTALDOCUMENTO);
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

					} // End of branch "NFEPRODUTO"

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
				 * [tLogRow_1 end ] start
				 */

				currentComponent = "tLogRow_1";

				// ////

				java.io.PrintStream consoleOut_tLogRow_1 = null;
				if (globalMap.get("tLogRow_CONSOLE") != null) {
					consoleOut_tLogRow_1 = (java.io.PrintStream) globalMap
							.get("tLogRow_CONSOLE");
				} else {
					consoleOut_tLogRow_1 = new java.io.PrintStream(
							new java.io.BufferedOutputStream(System.out));
					globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_1);
				}

				consoleOut_tLogRow_1
						.println(util_tLogRow_1.format().toString());
				consoleOut_tLogRow_1.flush();
				// ////
				globalMap.put("tLogRow_1_NB_LINE", nb_line_tLogRow_1);

				// /////////////////////

				ok_Hash.put("tLogRow_1", true);
				end_Hash.put("tLogRow_1", System.currentTimeMillis());

				/**
				 * [tLogRow_1 end ] stop
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
				 * [tLogRow_1 finally ] start
				 */

				currentComponent = "tLogRow_1";

				/**
				 * [tLogRow_1 finally ] stop
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
		final conn_nfsproduto conn_nfsprodutoClass = new conn_nfsproduto();

		int exitCode = conn_nfsprodutoClass.runJobInTOS(args);

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
			java.io.InputStream inContext = conn_nfsproduto.class
					.getClassLoader().getResourceAsStream(
							"client/conn_nfsproduto_0_1/contexts/"
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
					+ " bytes memory increase when running : conn_nfsproduto");
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
 * 300922 characters generated by Talend Open Studio for Data Integration on the
 * 28 de Fevereiro de 2019 13h40min30s BRT
 ************************************************************************************************/
