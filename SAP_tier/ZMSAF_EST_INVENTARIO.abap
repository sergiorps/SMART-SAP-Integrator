FUNCTION ZMSAF_EST_INVENTARIO.
*"----------------------------------------------------------------------
*"*"Interface local:
*"  IMPORTING
*"     VALUE(I_EMPRESA) TYPE  BUKRS
*"     VALUE(I_DATADE) TYPE  J_1BDOCDAT
*"     VALUE(I_DATAATE) TYPE  J_1BDOCDAT
*"     VALUE(I_MATERIAL) TYPE  MATNR OPTIONAL
*"  TABLES
*"      T_DADOS STRUCTURE  ZMSAF_EST_INVENTARIO
*"----------------------------------------------------------------------
DATA w_dados   type ZMSAF_EST_INVENTARIO.
DATA ti_mbew   TYPE TABLE OF mbew.
DATA wa_mbew   like line of ti_mbew.
DATA ti_mbewh  TYPE TABLE OF mbewh WITH HEADER LINE.
DATA wa_mbewh  like line of ti_mbewh.
DATA ti_t001k  TYPE TABLE OF t001k WITH HEADER LINE.
DATA dvar_data type d.

RANGES: s_BWKEY FOR t001k-BWKEY.

**Seleciona os Centros
  select bwkey bukrs bwmod
    from t001k
    INTO CORRESPONDING FIELDS OF TABLE ti_t001k
   where bukrs = I_EMPRESA.

    CHECK NOT ti_t001k[] IS INITIAL.

    loop at ti_t001k.
      clear s_BWKEY.
      s_BWKEY-sign = 'I'.
      s_BWKEY-option = 'EQ'.
      s_BWKEY-low = ti_t001k-bwkey.
      COLLECT s_BWKEY.
    ENDLOOP.

**Seleciona os registros da MBEW
   if I_MATERIAL is initial.
     refresh ti_mbew.
     select matnr bwkey bwtar lbkum salk3 vprsv verpr
            stprs peinh bklas salkv lfgja lfmon bwtty
            mtuse mtorg ownpr
       from mbew
       INTO CORRESPONDING FIELDS OF TABLE ti_mbew
       where bwkey in s_BWKEY.
   else.
     refresh ti_mbew.
     select matnr bwkey bwtar lbkum salk3 vprsv verpr
            stprs peinh bklas salkv lfgja lfmon bwtty
            mtuse mtorg ownpr
       from mbew
       INTO CORRESPONDING FIELDS OF TABLE ti_mbew
      where matnr = I_MATERIAL
        and bwkey in s_BWKEY.
   endif.

   sort ti_mbew by matnr bwkey ASCENDING.

   loop at ti_mbew into wa_mbew.

**Busca se existe Quantidade na MBEWH, se não existir leva o valor da MBEW
      select * from
       mbewh up to 1 rows
        INTO CORRESPONDING FIELDS OF TABLE ti_mbewh
       where matnr = wa_mbew-matnr
         and bwkey = wa_mbew-bwkey
         and bwtar = wa_mbew-bwtar
         and lfgja => I_DATAATE(4)
         and lfmon => I_DATADE+4(2).

     if sy-subrc = 0.
       if ti_mbewh-lbkum ne 0.
         w_dados-INCODIGOEMPRESA = '001'.

           SELECT single konts
             into w_dados-INCONTAANALITICA
             FROM t030
             WHERE ktopl = 'RIZO'
             AND ktosl = 'BSX'
             AND bklas = wa_mbewh-bklas.

         w_dados-INCCUSTO = ''.

         dvar_data = I_DATAATE.
         PERFORM calc_lastday USING dvar_data.
         w_dados-INDATAINVENTARIO = dvar_data.

         w_dados-INCODIGOPRODUTO = wa_mbewh-matnr.

           select single steuc
             into w_dados-INCLASSIFICACAOFISCAL
             from marc
            where matnr = wa_mbewh-matnr.

           select single MEINS
             into w_dados-INUNIDADEMEDIDA
             from mara
            where matnr = wa_mbewh-matnr.

         w_dados-INQTDE = ti_mbewh-lbkum.
         w_dados-INVALORCUSTOTOTAL = ti_mbewh-salk3.
         w_dados-INVALORCUSTOUNITARIO = ti_mbewh-salkv.
         w_dados-INNATUREZA = ''.
         w_dados-INSITUACAO = '1'.
         w_dados-INCODIGOPARTICIPANTE = ''.
         w_dados-INCNPJ = ''.
         w_dados-ININSCRICAO = ''.
         w_dados-INUF = ''.
         w_dados-INQTDEINICIAL = '0'.
         w_dados-INICMPROPRIO = '0'.
         w_dados-INICMST = '0'.
         w_dados-INOBSFISCAL = ''.
       endif.
     else.
       if wa_mbew-lbkum ne 0.
         w_dados-INCODIGOEMPRESA = '001'.

           SELECT single konts
             into w_dados-INCONTAANALITICA
             FROM t030
             WHERE ktopl = 'RIZO'
             AND ktosl = 'BSX'
             AND bklas = wa_mbew-bklas.

         w_dados-INCCUSTO = ''.

         dvar_data = I_DATAATE.
         PERFORM calc_lastday USING dvar_data.
         w_dados-INDATAINVENTARIO = dvar_data.

         w_dados-INCODIGOPRODUTO = wa_mbew-matnr.

           select single steuc
             into w_dados-INCLASSIFICACAOFISCAL
             from marc
            where matnr = wa_mbew-matnr.

           select single MEINS
             into w_dados-INUNIDADEMEDIDA
             from mara
            where matnr = wa_mbew-matnr.

         w_dados-INQTDE = wa_mbew-lbkum.
         w_dados-INVALORCUSTOTOTAL = wa_mbew-salk3.
         w_dados-INVALORCUSTOUNITARIO = wa_mbew-salkv.
         w_dados-INNATUREZA = ''.
         w_dados-INSITUACAO = '1'.
         w_dados-INCODIGOPARTICIPANTE = ''.
         w_dados-INCNPJ = ''.
         w_dados-ININSCRICAO = ''.
         w_dados-INUF = ''.
         w_dados-INQTDEINICIAL = '0'.
         w_dados-INICMPROPRIO = '0'.
         w_dados-INICMST = '0'.
         w_dados-INOBSFISCAL = ''.
       endif.
     endif.
     append w_dados to t_dados.
     ENDLOOP.
ENDFUNCTION.

*&---------------------------------------------------------------------*
*&      Form  calc_lastday
*&---------------------------------------------------------------------*
FORM calc_lastday USING dvar_data.

  DATA: pdata type d,
        nvar_anoc(4) TYPE n,           "Ano para cálculo de data
        nvar_mesc(2) TYPE n,           "Mês para cálculo de data
        dvar_lastdaymonth TYPE d.      "Último dia do mês (aaaammdd).

      pdata = dvar_data.
  nvar_mesc = pdata+4(2).
  nvar_anoc = pdata(4).

  ADD 1 TO nvar_mesc.

  IF nvar_mesc = 13.
    nvar_mesc = '01'.
    ADD 1 TO nvar_anoc.
  ENDIF.

  CONCATENATE nvar_anoc nvar_mesc '01' INTO dvar_lastdaymonth.
  dvar_lastdaymonth = dvar_lastdaymonth - 1.
  dvar_data = dvar_lastdaymonth.

ENDFORM.                    " calc_lastday