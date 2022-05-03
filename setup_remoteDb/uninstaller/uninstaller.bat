::This is lms_pc uninstaller
echo "NOTE:- This will uninstall Login Monitor Software with Parental Controls from this PC.But it will not remove your account details from our database.To remove your credentials and login history please reset your account from the software and then run this Uninstaller"

@echo off
setlocal
:PROMPT
SET /P AREYOUSURE=Are you sure to uninstall the software (Y/[N])?
IF /I "%AREYOUSURE%" NEQ "Y" GOTO END

powershell.exe Start-Process "u_batch.bat" -verb RunAs

:END
endlocal
