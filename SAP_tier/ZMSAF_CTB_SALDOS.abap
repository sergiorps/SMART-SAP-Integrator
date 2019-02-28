FUNCTION ZMSAF_CTB_SALDOS.
*"----------------------------------------------------------------------
*"*"Interface local:
*"  IMPORTING
*"     VALUE(I_EMPRESA) TYPE  BUKRS
*"     VALUE(I_EXERCICIO) TYPE  GJAHR
*"     VALUE(I_DATADE) TYPE  MONAT
*"     VALUE(I_DATAATE) TYPE  MONAT
*"  TABLES
*"      E_SALDOS STRUCTURE  ZMSAF_CTB_SALDOS
*"----------------------------------------------------------------------
  DATA t_flex TYPE TABLE OF faglflext.
  DATA w_flex LIKE LINE OF t_flex.
  DATA w_datos TYPE ZMSAF_CTB_SALDOS.
  DATA l_period(02) TYPE n.
  DATA l_campo TYPE fieldname.
  DATA s_monat TYPE RANGE OF monat WITH HEADER LINE.
  DATA s_gjahr TYPE RANGE OF gjahr WITH HEADER LINE.
  DATA s_bukrs TYPE RANGE OF bukrs WITH HEADER LINE.
  DATA w_saldo type ZMSAF_CTB_SALDOS.
  data l_inicial type faglflext-hslvt.

  FIELD-SYMBOLS <valor> TYPE faglflext-hsl01.

  REFRESH s_bukrs.
  IF I_EMPRESA IS NOT INITIAL.
    s_bukrs-sign   = 'I'.
    s_bukrs-option = 'EQ'.
    s_bukrs-low    = I_EMPRESA.
    APPEND s_bukrs.
  ENDIF.

  REFRESH s_gjahr.
  IF I_EXERCICIO IS NOT INITIAL.
    s_gjahr-sign   = 'I'.
    s_gjahr-option = 'EQ'.
    s_gjahr-low    = I_EXERCICIO.
    APPEND s_gjahr.
  ENDIF.

  REFRESH s_monat.
  IF I_DATADE IS INITIAL AND I_DATAATE IS NOT INITIAL.
    s_monat-sign    = 'I'.
    s_monat-option  = 'BT'.
    CLEAR s_monat-low.
    s_monat-high    = I_DATAATE.
    APPEND s_monat.
  ELSEIF I_DATADE IS INITIAL AND I_DATAATE IS INITIAL.
    s_monat-sign    = 'I'.
    s_monat-option  = 'EQ'.
    s_monat-low     = I_DATADE.
    CLEAR s_monat-high.
    APPEND s_monat.
  ELSEIF I_DATADE IS NOT INITIAL AND I_DATAATE IS NOT INITIAL.
    s_monat-sign    = 'I'.
    s_monat-option  = 'BT'.
    s_monat-low     = I_DATADE.
    s_monat-high    = I_DATAATE.
    APPEND s_monat.
  ENDIF.

  SELECT * INTO TABLE t_flex
   FROM faglflext
    WHERE rbukrs IN s_bukrs
      AND ryear  IN s_gjahr.

  LOOP AT t_flex INTO w_flex.
    CLEAR w_datos.

    w_datos-sdcodigoempresa     = w_flex-rbukrs.
    w_datos-sdcontaanalitica    = w_flex-racct.

    l_campo = 'HSLVT'.
    ASSIGN COMPONENT l_campo OF STRUCTURE w_flex TO <valor>.
    IF sy-subrc EQ 0.
      l_inicial = <valor>.
    ENDIF.

    DO 16 TIMES.
      l_period = sy-index.
      CHECK l_period IN s_monat.
      CONCATENATE l_period w_flex-ryear INTO w_datos-sddatasaldoinicial.

      CONCATENATE 'HSL' l_period INTO l_campo.
      ASSIGN COMPONENT l_campo OF STRUCTURE w_flex TO <valor>.
      IF sy-subrc EQ 0.
         IF w_flex-drcrk = 'H'.
           w_datos-sdvalortotalcredito = <valor> * -1.
         ELSE.
           w_datos-sdvalortotaldebito = <valor>.
         ENDIF.
         clear l_inicial.
      ENDIF.

      read table e_saldos with key SDCONTAANALITICA = w_flex-racct SDDATASALDOINICIAL = w_datos-sddatasaldoinicial.
      if sy-subrc = 0.
         e_saldos-sdvalortotaldebito  = e_saldos-sdvalortotaldebito  + w_datos-sdvalortotaldebito.
         e_saldos-sdvalortotalcredito = e_saldos-sdvalortotalcredito + w_datos-sdvalortotalcredito.

         e_saldos-sdvalorsaldofinal = e_saldos-sdvalortotaldebito - e_saldos-sdvalortotalcredito.
         IF e_saldos-sdvalorsaldofinal > 0.
           e_saldos-sdindicadordcdbfinal = 'D'.
         ELSE.
           e_saldos-sdindicadordcdbfinal = 'C'.
         ENDIF.
         modify e_saldos index sy-tabix.
      else.
         w_datos-sdvalorsaldofinal = w_datos-sdvalortotaldebito - w_datos-sdvalortotalcredito.
         IF w_datos-sdvalorsaldofinal > 0.
           w_datos-sdindicadordcdbfinal = 'D'.
         ELSE.
           w_datos-sdindicadordcdbfinal = 'C'.
         ENDIF.
        append w_datos to e_saldos.
      endif.
    ENDDO.

  ENDLOOP.
ENDFUNCTION.