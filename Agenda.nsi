;--------------------------------
;Incluimos el Modern UI

  !include "MUI2.nsh"
  !include nsDialogs.nsh
  !include LogicLib.nsh

;--------------------------------
;Propiedades de la interfaz

  !define MUI_ABORTWARNING
  !define NOMBREAPP "Agenda"
  !define HOSTPORT "%"

;--------------------------------
#General

;Nombre de la aplicacion y del ejecutable
   Name "${NOMBREAPP}"
   Icon "agenda.ico"
   OutFile "Agenda.exe"

;Directorio de instalacion
   DirText "Elija un directorio donde instalar la aplicacion:"
   InstallDir "$PROGRAMFILES\${NOMBREAPP}"

;Obtenemos el directorio del registro (si esta disponible)
   InstallDirRegKey HKCU "Software\Agenda" ""
  
;Indicamos que cuando la instalacion se complete no se cierre el instalador automaticamente
   AutoCloseWindow false

;Si se encuentran archivos existentes se sobreescriben
   SetOverwrite on
   SetDatablockOptimize on

;--------------------------------
#Paginas
;paginas referentes al instalador
  !insertmacro MUI_PAGE_COMPONENTS
  !insertmacro MUI_PAGE_DIRECTORY
  Page custom pgPageCreate pgPageLeave
  !insertmacro MUI_PAGE_INSTFILES

;paginas referentes al desinstalador
  !insertmacro MUI_UNPAGE_CONFIRM
  !insertmacro MUI_UNPAGE_INSTFILES

;--------------------------------
#Lenguajes
;Definimos el idioma del instalador
  !insertmacro MUI_LANGUAGE "Spanish"

;--------------------------------
;Variables

Var /GLOBAL Dialog
Var /GLOBAL TextUser
Var /GLOBAL TextPass
Var /GLOBAL TextUserR
Var /GLOBAL TextPassR

#Secciones

Section "Agenda" agenda

  SetOutPath "$INSTDIR"
;Hacemos que esta seccion tenga que instalarse obligatoriamente
  SectionIn RO 

;Incluimos todos los archivos que componen nuestra aplicacion

  ;Archivos a instalar (solo archivos, los ejecutables van en la seccion "prerequisitos"
  File agenda.jar
  File "agenda.ico"

;Menu inicio
  SetShellVarContext all
  createDirectory "$SMPROGRAMS\${NOMBREAPP}"
    createShortCut "$SMPROGRAMS\${NOMBREAPP}\${NOMBREAPP}.lnk" "$INSTDIR\Agenda.jar" "" "$INSTDIR\agenda.ico"
    createShortCut "$SMPROGRAMS\${NOMBREAPP}\Manual.lnk" "$INSTDIR\manual.pdf" "" ""
    createShortCut "$SMPROGRAMS\${NOMBREAPP}\Desinstalar.lnk" "$INSTDIR\Uninstall.exe" "" ""
    
;Acceso directo en el escritorio
  CreateShortCut "$DESKTOP\${NOMBREAPP}.lnk" "$INSTDIR\${NOMBREAPP}.jar" "" "$INSTDIR\agenda.ico"
  
;Hacemos que la instalacion se realice para todos los usuarios del sistema
  SetShellVarContext all

;Guardamos un registro de la carpeta instalada
  WriteRegStr HKCU "Software\Agenda" "" $INSTDIR
  
;Creamos un desintalador
  WriteUninstaller "$INSTDIR\Uninstall.exe"

  SetOutPath "$INSTDIR\reportes"
  File "reportes\ReporteAgenda.jasper"
  File "reportes\ReporteUbicacion.jasper"
SectionEnd


#Seccion desinstalador

Section "Uninstall"

SetShellVarContext all

;Borramos el ejecutable del menu inicio
  delete "$SMPROGRAMS\${NOMBREAPP}\${NOMBREAPP}.lnk"
  delete "$SMPROGRAMS\${NOMBREAPP}\Manual.lnk"
  delete "$SMPROGRAMS\${NOMBREAPP}\Desinstalar.lnk"

;Borramos el acceso directo del escritorio
  delete "$DESKTOP\${NOMBREAPP}.lnk"

;Intentamos borrar el menu inicio (Solo se puede hacer si la carpeta esta vacio)
  rmDir "$SMPROGRAMS\${NOMBREAPP}"
 
;Archivos a desinstalar
    delete $INSTDIR\reportes\ReporteAgenda.jasper
    delete $INSTDIR\reportes\ReporteUbicacion.jasper
    RMDir /r $INSTDIR\reportes
    delete $INSTDIR\PDFReaderSetup.exe
    delete $INSTDIR\jre.exe
    delete $INSTDIR\agenda.jar
    delete $INSTDIR\manual.pdf
    delete $INSTDIR\agenda.ico
 
;Borramos el desinstalador
  delete $INSTDIR\Uninstall.exe
 
;Intentamos borrar la carpeta de instalacion (Solo se puede si esta vacia)
  rmDir $INSTDIR

  DeleteRegKey /ifempty HKCU "Agenda"

SectionEnd


#Seccion Prerequisitos, ejecucion de otros instaladores 

Section "Prerequisitos" prerequisitos

  SetOutPath "$INSTDIR"
   
   # Make the directory "$INSTDIR" read write accessible by all users
  AccessControl::GrantOnFile "$INSTDIR" "(BU)" "GenericRead + GenericWrite"

SectionIn RO
DetailPrint "Comenzando la instalacion de Java"     
    File "dependencia\jre.exe"
    ExecWait "dependencia\jre.exe /s"

DetailPrint "Comenzando la instalacion reader PDF"     
    File "dependencia\PDFReaderSetup.exe"
    ExecWait "dependencia\PDFReaderSetup.exe /VERYSILENT /NORESTART"

DetailPrint "Comenzando la instalacion de MariaDB Server"
    SetOutPath $TEMP

    
    ;MariaDB
    File "dependencia\mariadb-10.2.14-win32.msi"
    ExecWait 'msiexec /i $TEMP\mariadb-10.2.14-win32.msi SERVICENAME=mariadb10 PASSWORD=$TextPassR ADDLOCAL=DBInstance,MYSQLSERVER,Client /qn'

    File "dependencia\scriptAgenda.sql"
    ExpandEnvStrings $0 %COMSPEC%
    ExecWait '"$0" /C "$PROGRAMFILES\MariaDB 10.2\bin\mysql" -u root -p$TextPassR < $TEMP\scriptAgenda.sql'

    ExecWait '"$PROGRAMFILES\MariaDB 10.2\bin\mysql" -uroot -p$TextPassR --execute "rename user `root`@`localhost` to `$TextUserR`@`localhost`";'
    ExecWait '"$PROGRAMFILES\MariaDB 10.2\bin\mysql" -u$TextUserR -p$TextPassR --execute "FLUSH PRIVILEGES";'

SectionEnd  



#Seccion "Manual de usuario"

Section "Manual de usuario" manual

SetOutPath "$INSTDIR"

;Archivos a instalar
  File manual.pdf

SectionEnd

Function pgPageCreate
    !insertmacro MUI_HEADER_TEXT "Database Settings" "Provide MariaDB config and install directory."

    nsDialogs::Create 1018
    Pop $Dialog

    ${If} $Dialog == error
        Abort
    ${EndIf}

    ${NSD_CreateGroupBox} 10% 10u 80% 62u "MySQL Database Settings"
    Pop $0

        ${NSD_CreateLabel} 20% 26u 20% 10u "Username:"
        Pop $0

        ${NSD_CreateText} 40% 24u 40% 12u "postgres"
        Pop $TextUser

        ${NSD_CreateLabel} 20% 40u 20% 10u "Password:"
        Pop $0

        ${NSD_CreatePassword} 40% 38u 40% 12u ""
        Pop $TextPass

    nsDialogs::Show
FunctionEnd

Function PgPageLeave
    ${NSD_GetText} $TextUser $TextUserR
    ${NSD_GetText} $TextPass $TextPassR
FunctionEnd


;--------------------------------
#Descripciones

  ;Descripcion de Agenda
  LangString DESC_Agenda ${LANG_SPANISH} "Archivos necesarios para la ejecucion de la Agenda"

  ;Descripcion de Prerequisitos
  LangString DESC_Prerequisitos ${LANG_SPANISH} "Archivos necesarios para que Agenda funcione correctamente"

  ;Descripcion de Manual
  LangString DESC_Manual ${LANG_SPANISH} "Manual de usuario"

  ;Asignamos las descripciones a cada seccion
  !insertmacro MUI_FUNCTION_DESCRIPTION_BEGIN
    !insertmacro MUI_DESCRIPTION_TEXT ${Agenda} $(DESC_Agenda)
    !insertmacro MUI_DESCRIPTION_TEXT ${Prerequisitos} $(DESC_Prerequisitos)
    !insertmacro MUI_DESCRIPTION_TEXT ${Manual} $(DESC_Manual)
  !insertmacro MUI_FUNCTION_DESCRIPTION_END

;--------------------------------