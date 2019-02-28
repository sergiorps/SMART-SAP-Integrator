FUNCTION ZMSAF_CTB_LANCTOS.
*"----------------------------------------------------------------------
*"*"Interface local:
*"  IMPORTING
*"     VALUE(I_EMPRESA) TYPE  BUKRS OPTIONAL
*"     VALUE(I_DATADE) TYPE  BUDAT OPTIONAL
*"     VALUE(I_DATAATE) TYPE  BUDAT
*"  TABLES
*"      E_LANCTOS STRUCTURE  ZMSAF_CTB_LANCTOS
*"----------------------------------------------------------------------
DATA s_bstat TYPE RANGE OF bkpf-bstat.
DATA t_bkpf TYPE TABLE OF bkpf.
DATA w_bkpf LIKE LINE OF t_bkpf.
DATA t_bseg TYPE TABLE OF bseg.
DATA w_bseg LIKE LINE OF t_bseg.
DATA w_datos TYPE ZMSAF_CTB_LANCTOS.

data s_budat type range of budat WITH HEADER LINE.

  refresh s_budat.
  if I_DATADE is initial and I_DATAATE is not initial.
    s_budat-sign    = 'I'.
    s_budat-option  = 'BT'.
    clear s_budat-low.
    s_budat-high    = I_DATAATE.
    append s_budat.
  elseif I_DATADE is initial and I_DATAATE is initial.
    s_budat-sign    = 'I'.
    s_budat-option  = 'EQ'.
    s_budat-low     = I_DATADE.
    clear s_budat-high.
    append s_budat.
  elseif I_DATADE is not initial and I_DATAATE is not initial.
    s_budat-sign    = 'I'.
    s_budat-option  = 'BT'.
    s_budat-low     = I_DATADE.
    s_budat-high    = I_DATAATE.
    append s_budat.
  endif.


  SELECT * INTO TABLE t_bkpf
    FROM bkpf
   WHERE bukrs = I_EMPRESA
     AND bstat IN s_bstat
     AND budat IN s_budat.

  SELECT * INTO TABLE t_bseg
    FROM bseg
     FOR ALL ENTRIES IN t_bkpf
   WHERE bukrs = t_bkpf-bukrs
     AND belnr = t_bkpf-belnr
     AND gjahr = t_bkpf-gjahr.

  LOOP AT t_bseg INTO w_bseg.
      CLEAR w_datos.
      READ TABLE t_bkpf INTO w_bkpf WITH KEY bukrs = w_bseg-bukrs belnr = w_bseg-belnr gjahr = w_bseg-gjahr.
      w_datos-lccodigoempresa          = w_bseg-bupla.
      w_datos-lccontaanalitica         = w_bseg-hkont.
      w_datos-lcccusto                 = w_bseg-kostl.
      w_datos-lcdatalancamento         = w_bkpf-budat.
      w_datos-lcvalorlancamento        = w_bseg-wrbtr.
      IF w_bseg-shkzg = 'H'.
        w_datos-lcdebitocredito        = 'C'.
      ELSE.
        w_datos-lcdebitocredito        = 'D'.
      ENDIF.
      w_datos-lcnarquivamento          = w_bkpf-xblnr.
      w_datos-lcparticipante           = w_bseg-lifnr.
      w_datos-lctipolancto             = w_bkpf-blart.
      w_datos-lchistoricocomplementar  = w_bseg-sgtxt.
      APPEND w_datos TO e_lanctos.
  ENDLOOP.

ENDFUNCTION.