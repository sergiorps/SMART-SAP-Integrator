FUNCTION ZMSAF_FIS_NFEPROD.
*"----------------------------------------------------------------------
*"*"Interface local:
*"  IMPORTING
*"     VALUE(I_EMPRESA) TYPE  BUKRS
*"     VALUE(I_DATADE) TYPE  J_1BDOCDAT
*"     VALUE(I_DATAATE) TYPE  J_1BDOCDAT
*"  TABLES
*"      T_DATOS STRUCTURE  ZMSAF_FIS_NFEPROD
*"----------------------------------------------------------------------
DATA t_docs TYPE TABLE OF j_1bnfdoc.
DATA w_docs LIKE LINE OF t_docs.
DATA s_docdat TYPE RANGE OF j_1bdocdat WITH HEADER LINE.
DATA w_datos TYPE zmsaf_fis_nfeprod.
DATA w_active TYPE j_1bnfe_active.
DATA t_tax TYPE TABLE OF j_1bnfstx.
DATA w_tax TYPE j_1bnfstx.
DATA t_items TYPE TABLE OF j_1bnflin.
DATA w_items LIKE LINE OF t_items.

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

 LOOP AT t_docs INTO w_docs.

   CLEAR w_datos.

   SELECT SINGLE * INTO w_active
     FROM j_1bnfe_active
     WHERE docnum = w_docs-docnum.

   w_datos-facodigoempresa   = w_docs-bukrs.
   w_datos-faemitente        = w_docs-ind_emit.
   w_datos-famodelodocumento = w_docs-model.
   IF w_docs-nftype = 'Z1'.
     w_datos-fatipodocumento   = 'CTR'.
   ELSE.
     w_datos-fatipodocumento   = 'NFE'.
   ENDIF.
   w_datos-faseriedocumento  = w_docs-series.
   w_datos-fanumerodocumento = w_docs-nfenum.
   CONCATENATE w_active-regio w_active-nfyear w_active-nfmonth w_active-stcd1
               w_active-model w_active-serie w_active-nfnum9 w_active-tpemis
               w_active-docnum9 w_active-cdv INTO w_datos-fanfechave.
   w_datos-fadataemissao     = w_docs-docdat.
   w_datos-fadataentrada     = w_docs-pstdat.
   w_datos-fadatasaida       = w_docs-dsaient.
   w_datos-faufemitente      = w_docs-regio.
   w_datos-fasituacaodoc     = w_docs-cod_sit.
   w_datos-faindcancelamento = w_docs-cancel.
   w_datos-fanotacomplem     = 'E2'.
   w_datos-faconsumidor     = w_docs-ind_final.
   IF w_docs-stains IS NOT INITIAL.
     w_datos-facontribuinte   = 'X'.
   ENDIF.

   w_datos-fainscricaosubstituto = space.

   SELECT SINGLE ndi INTO w_datos-fanumerodeclaracaoimportacao
     FROM j_1bnfimport_di
     WHERE docnum = w_docs-docnum.
   CASE w_docs-indpag.
     WHEN 0.
       w_datos-fatipofatura = 'V'.
     WHEN 1.
       w_datos-fatipofatura = 'P'.
     WHEN 2.
       w_datos-fatipofatura = '9'.
   ENDCASE.
   w_datos-favalortotalnfiscal  = w_docs-nftot.
*   w_datos-favalordesconto      = w_docs-nfdis.
*   w_datos-favalortotalprodutos  = w_docs-nfnet.

   SELECT * INTO TABLE t_tax
     FROM j_1bnfstx
     WHERE docnum = w_docs-docnum.

   SELECT * INTO TABLE t_items
     FROM j_1bnflin
     WHERE docnum = w_docs-docnum.

   LOOP AT t_tax INTO w_tax WHERE taxtyp = 'ICMS'.
     w_datos-fabaseicm  = w_datos-fabaseicm + w_tax-base.
     w_datos-fatotalicm  = w_datos-fatotalicm + w_tax-taxval.
   ENDLOOP.
   LOOP AT t_tax INTO w_tax WHERE taxtyp = 'IPI'.
     w_datos-favalorbasereducaoipi  = w_datos-favalorbasereducaoipi + w_tax-base.
     w_datos-favaloripi             = w_datos-favaloripi + w_tax-taxval.
   ENDLOOP.

   LOOP AT t_items INTO w_items.
     w_datos-favalorfrete  = w_datos-favalorfrete  + w_items-nffre.
     w_datos-favalorseguro = w_datos-favalorseguro + w_items-nfins.
     w_datos-favaloroutras = w_datos-favaloroutras + w_items-nfoth.
   ENDLOOP.

    w_datos-faviatransporte         = w_docs-traty.
    w_datos-famodalidadefrete       = w_docs-inco1.

    SELECT SINGLE parid INTO w_datos-facodigotransportador
      FROM j_1bnfnad
      WHERE docnum = w_docs-docnum
        AND parvw  = 'SP'.

    w_datos-faqtdevolumes           = w_docs-anzpk.
    w_datos-faespecievolumes        = w_docs-shpunt.
    w_datos-famarcavolumes          = w_docs-shpmrk.
    w_datos-fanumeracaovolumes      = w_docs-anzpk.
    w_datos-fapesobruto             = w_docs-brgew.
    w_datos-fapesoliquido           = w_docs-ntgew.
    w_datos-faidentificacaoveiculo  = w_docs-traid.

    CLEAR w_tax.
    READ TABLE t_tax INTO w_tax WITH KEY docnum = w_docs-docnum taxgrp = 'PIS'.
    IF sy-subrc = 0.
      w_datos-fabasepis  = w_tax-base.
      w_datos-favalorpis = w_tax-taxval.
      w_datos-fapisnaocreditado = w_tax-othbas.
    ENDIF.

    CLEAR w_tax.
    READ TABLE t_tax INTO w_tax WITH KEY docnum = w_docs-docnum taxgrp = 'COFI'.
    IF sy-subrc = 0.
      w_datos-fabasecofins  = w_tax-base.
      w_datos-favalorcofins = w_tax-taxval.
      w_datos-facofinsnaocreditado = w_tax-othbas.
    ENDIF.
    w_datos-itdescrcomplementar = space.
    w_datos-fatiponota = '1'.
    w_datos-fanfiscalreferencia = w_docs-docref.

    CLEAR w_tax.
    READ TABLE t_tax INTO w_tax WITH KEY docnum = w_docs-docnum taxgrp = 'ICMS'.
    IF sy-subrc = 0.
      w_datos-facomplementoicm = w_tax-taxval.
    ENDIF.


    LOOP AT t_items INTO w_items.
      w_datos-itnumeroitem                = w_items-itmnum.
      w_datos-itservico                   = 'M'.
      w_datos-itcodigoproduto             = w_items-matnr.
      w_datos-itcfop                      = w_items-cfop.
      w_datos-itcfopcomplementar          = w_items-cfop.
      w_datos-itclassificacaofiscal       = w_items-nbm.
      w_datos-itsituacaotributariafederal  = w_items-taxlw2.
      w_datos-itsituacaotributariaestadual = w_items-taxlw1.
      w_datos-itqtdeproduto               = w_items-menge.
      w_datos-itunidademedida             = w_items-meins.
      w_datos-itvalorunitario             = w_items-netpr.
      w_datos-itprecototal                = w_items-netwr.
      w_datos-itvalordesconto             = w_items-netdis.
      w_datos-itvalordespesafrete         = w_items-netfre.
      w_datos-itvalordespesaseguro        = w_items-netins.
      w_datos-itvaloroutrasdespesas       = w_items-netoth.
      CASE w_items-ind_mov.
        WHEN 0.
        w_datos-itindmovimentacao           = space.
        WHEN 1.
          w_datos-itindmovimentacao           = 'N'.
      ENDCASE.
      w_datos-itmovimentacaoestoque = w_items-ind_mov.

      READ TABLE t_tax INTO w_tax WITH KEY docnum = w_docs-docnum itmnum = w_items-itmnum taxgrp = 'ICMS'.
      IF sy-subrc = 0.
        w_datos-itvalorbaseicm  = w_tax-base.
        w_datos-italiquotaicm   = w_tax-rate.
        w_datos-itvaloricm      = w_tax-taxval.
        w_datos-itvalorisentoicm = w_tax-excbas.
        w_datos-itvaloroutroicm  = w_tax-othbas.
      ENDIF.

      READ TABLE t_tax INTO w_tax WITH KEY docnum = w_docs-docnum itmnum = w_items-itmnum taxtyp = 'ICM2'.
      IF sy-subrc = 0.
        w_datos-itvalorbaseentrada     = w_tax-othbas.
        w_datos-italiquotaentrada      = w_tax-rate.
        w_datos-iticmnaocreditado      = w_tax-taxval.
      ENDIF.

      READ TABLE t_tax INTO w_tax WITH KEY docnum = w_docs-docnum itmnum = w_items-itmnum taxtyp = 'ICS3'.
      IF sy-subrc = 0.
        w_datos-itvalorbasesubstituicao = w_tax-base.
        w_datos-italiquotasubstituicao  = w_tax-rate.
        w_datos-itvaloricmsubstituicao  = w_tax-taxval.
        w_datos-itbaseicmstnaocred      = w_tax-othbas.
        w_datos-itvaloricmstnaocred     = w_tax-rate.
        w_datos-itcreditosuspenso       = w_tax-taxval.
        w_datos-itmva                   = 0.
      ENDIF.

      READ TABLE t_tax INTO w_tax WITH KEY docnum = w_docs-docnum itmnum = w_items-itmnum taxgrp = 'IPI'.
      IF sy-subrc = 0.
        w_datos-itformacalculoipi   = '1'.
        w_datos-itvalorbaseipi      = w_tax-base.
        w_datos-italiquotaipi       = w_tax-rate.
        w_datos-itvaloripi          = w_tax-taxval.
        w_datos-itvalorisentoipi    = w_tax-excbas.
        w_datos-itvaloroutroipi     = w_tax-othbas.
        w_datos-itipinaocreditado   = w_tax-base.
      ENDIF.

      READ TABLE t_tax INTO w_tax WITH KEY docnum = w_docs-docnum itmnum = w_items-itmnum taxgrp = 'PIS'.
      IF sy-subrc = 0.
*        w_datos-itformacalculopis = w_tax-ipipauta.
        w_datos-itbasepis         = w_tax-base.
        w_datos-italiquotapis     = w_tax-rate.
        w_datos-itvalorpis        = w_tax-taxval.
        w_datos-itpisnaocreditado = w_tax-othbas.
      ENDIF.

      w_datos-itsittribpis = w_items-taxlw5+1.

      READ TABLE t_tax INTO w_tax WITH KEY docnum = w_docs-docnum itmnum = w_items-itmnum taxgrp = 'COFI'.
      IF sy-subrc = 0.
*        w_datos-itformacalculocofins = w_tax-ipipauta.
        w_datos-itbasecofins         = w_tax-base.
        w_datos-italiquotacofins     = w_tax-rate.
        w_datos-itvalorcofins        = w_tax-taxval.
        w_datos-itcofinsnaocreditado = w_tax-othbas.
      ENDIF.

      SELECT SINGLE akont INTO w_datos-itcodigoctb
       FROM lfb1
       WHERE lifnr = w_docs-parid.

      w_datos-itsittribcofins = w_items-taxlw4+1.
      w_datos-itvalorcontabilicm = w_items-netwr.

      APPEND w_datos TO t_datos.
    ENDLOOP.
 ENDLOOP.




ENDFUNCTION.