FUNCTION ZMSAF_FIS_NFSPROD.
*"----------------------------------------------------------------------
*"*"Interface local:
*"  IMPORTING
*"     VALUE(I_EMPRESA) TYPE  BUKRS
*"     VALUE(I_DATADE) TYPE  J_1BDOCDAT
*"     VALUE(I_DATAATE) TYPE  J_1BDOCDAT
*"  TABLES
*"      T_DATOS STRUCTURE  ZMSAF_FIS_NFSPROD
*"----------------------------------------------------------------------
DATA t_docs TYPE TABLE OF j_1bnfdoc.
DATA w_docs LIKE LINE OF t_docs.
DATA s_docdat TYPE RANGE OF j_1bdocdat WITH HEADER LINE.
data w_datos type ZMSAF_FIS_NFSPROD.
data w_active type j_1bnfe_active.
data t_tax type table of J_1BNFSTX.
data w_tax type J_1BNFSTX.
data t_items type table of J_1BNFLIN.
data w_items like line of t_items.
*J_1BNFDOC
*J_1BNFLIN

 IF I_DATADE IS NOT INITIAL AND I_DATAATE IS INITIAL.
     CLEAR s_docdat.
     s_docdat-sign   = 'I'.
     s_docdat-option = 'EQ'.
     s_docdat-low    = I_DATADE.
     APPEND s_docdat.
  ELSEIF I_DATADE IS INITIAL AND I_DATAATE IS NOT INITIAL.
     CLEAR s_docdat.
     s_docdat-sign   = 'I'.
     s_docdat-option = 'BT'.
     s_docdat-high   = I_DATAATE.
     APPEND s_docdat.
  ELSEIF I_DATADE IS NOT INITIAL AND I_DATAATE IS NOT INITIAL.
     CLEAR s_docdat.
     s_docdat-sign   = 'I'.
     s_docdat-option = 'BT'.
     s_docdat-low    = I_DATADE.
     s_docdat-high   = I_DATAATE.
     APPEND s_docdat.
 ELSE.
     REFRESH s_docdat.
 ENDIF.
 SELECT * INTO TABLE t_docs
   FROM j_1bnfdoc
   WHERE bukrs = I_EMPRESA
     AND docdat IN s_docdat.

 loop at t_docs into w_docs.

   clear w_datos.

   select single * into w_active
     from j_1bnfe_active
     where docnum = w_docs-docnum.

   w_datos-facodigoempresa   = w_docs-bukrs.
   w_datos-faemitente        = w_docs-cnpj_bupla.
   w_datos-famodelodocumento = w_docs-model.
   if w_docs-nftype = 'Z1'.
     w_datos-fatipodocumento   = 'CTR'.
   else.
     w_datos-fatipodocumento   = 'NFE'.
   endif.
   w_datos-faseriedocumento  = w_docs-series.
   w_datos-fanumerodocumento = w_docs-nfenum.
   concatenate w_active-regio w_active-nfyear w_active-nfmonth w_active-stcd1
               w_active-model w_active-serie w_active-nfnum9 w_active-tpemis
               w_active-docnum9 w_active-cdv into w_datos-fanfechave.
   w_datos-fadataemissao     = w_docs-pstdat.
   w_datos-fadatasaida       = w_docs-dsaient.
   w_datos-fahorasaida       = space.
   w_datos-facodigocliente   = w_docs-parid.
   w_datos-faufcliente       = w_docs-regio.
   w_datos-fasituacaodoc     = w_docs-cod_sit.
   if w_docs-candat is not initial.
     w_datos-faindcancelamento = 'C'.
   endif.
   w_datos-faconsumidor     = w_docs-ind_final.
   w_datos-facontribuinte   = w_docs-stains.
   w_datos-favalorprodutos  = w_docs-nftot.

   select * into table t_tax
     from J_1BNFSTX
     where docnum = w_docs-docnum.

   select * into table t_items
     from J_1BNFLIN
     where docnum = w_docs-docnum.

   loop at t_tax into w_tax where taxtyp = 'ICM3'.
     w_datos-fabaseicms  = w_datos-fabaseicms + w_tax-base.
     w_datos-fatotalicm  = w_datos-fatotalicm + w_tax-taxval.
   endloop.
   loop at t_tax into w_tax where taxtyp = 'IPI'.
     w_datos-fabasereducaoipi  = w_datos-fabasereducaoipi + w_tax-base.
     w_datos-favalortotalipi   = w_datos-favalortotalipi  + w_tax-taxval.
   endloop.

   loop at t_tax into w_tax where taxtyp = 'ICS3'.
     w_datos-fatotalbasesubst      = w_datos-fatotalbasesubst      + w_tax-base.
     w_datos-fatotalicmsubst       = w_datos-fatotalicmsubst       + w_tax-taxval.
   endloop.

   w_datos-fainscricaosubstituto = space.

   loop at t_items into w_items.
     w_datos-favalorfrete  = w_datos-favalorfrete  + w_items-nffre.
     w_datos-favalorseguro = w_datos-favalorseguro + w_items-nfins.
     w_datos-favaloroutras = w_datos-favaloroutras + w_items-nfoth.
   endloop.

    w_datos-faviatransporte         = w_docs-traty.
    w_datos-famodalidadefrete       = w_docs-modfrete.

    select single parid into w_datos-facodigotransportador
      from j_1bnfnad
      where docnum = w_docs-docnum
        and parvw  = 'SP'.
    w_datos-faqtdevolumes           = w_docs-anzpk.
    w_datos-faespecievolumes        = w_docs-shpunt.
    w_datos-famarcavolumes          = w_docs-shpmrk.
    w_datos-fanumeracaovolumes      = w_docs-anzpk.
    w_datos-fapesobruto             = w_docs-brgew.
    w_datos-fapesoliquido           = w_docs-ntgew.
    w_datos-faidentificacaoveiculo  = w_docs-traid.
    w_datos-fatiponota              = 1.

    loop at t_items into w_items.
      w_datos-itnumeroitem                = w_items-itmnum.
      w_datos-itcodigoproduto             = w_items-matnr.
      w_datos-itcfop                      = w_items-cfop.
      w_datos-itclassificacaofiscal       = w_items-nbm.
      w_datos-itsittributariafederal      = w_items-taxlw2.
      w_datos-itqtdeproduto               = w_items-menge.
      w_datos-itunidademedida             = w_items-meins.
      w_datos-itvalorprecounitario        = w_items-netpr.
      w_datos-itvalorprecototal           = w_items-netwr.
      w_datos-itvalordesconto             = w_items-netdis.
      w_datos-itdespesasfrete             = w_items-netfre.
      w_datos-itdespesasseguro            = w_items-netins.
      w_datos-itoutrasdespesas            = w_items-netoth.
      w_datos-itindmovimentacao           = w_items-ind_mov.

      read table t_tax into w_tax with key docnum = w_docs-docnum itmnum = w_items-itmnum taxgrp = 'ICMS'.
      if sy-subrc = 0.
        w_datos-itvalorbaseicms  = w_tax-base.
        w_datos-italiquotaicms   = w_tax-rate.
        w_datos-itvaloricms      = w_tax-taxval.
        w_datos-itvalorisentoicm = w_tax-excbas.
        w_datos-itvaloroutroicm  = w_tax-othbas.
      endif.
      w_datos-itvalorcontabilicm = w_items-netwr.

      read table t_tax into w_tax with key docnum = w_docs-docnum itmnum = w_items-itmnum taxgrp = 'PIS'.
      if sy-subrc = 0.
         w_datos-fabasepis        = w_tax-base.
        w_datos-itbasepis        = w_tax-base.
        w_datos-italiquotapis	   = w_tax-rate.
        w_datos-itvalorpis       = w_tax-taxval.
      endif.

      read table t_tax into w_tax with key docnum = w_docs-docnum itmnum = w_items-itmnum taxgrp = 'COFI'.
      if sy-subrc = 0.
        w_datos-fabasecofins     = w_tax-base.
        w_datos-itbasecofins     = w_tax-base.
        w_datos-italiquotacofins = w_tax-rate.
        w_datos-itvalorcofins    = w_tax-taxval.
      endif.

      w_datos-itdescrcomplementar = space.
      w_datos-itprecototal        = w_items-netwr.
      w_datos-itsituacaotributariafederal  = w_items-taxlw2+1.
      w_datos-itsituacaotributariaestadual = w_items-taxlw1+1.
      w_datos-itsittribcofins              = w_items-taxlw4+1.
      w_datos-itsittribpis                 = w_items-taxlw5+1.

      append w_datos to t_datos.
    endloop.
 endloop.
ENDFUNCTION.