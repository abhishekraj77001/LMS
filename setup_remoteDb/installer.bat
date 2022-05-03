::this is lms_pc_installer
@echo off
cd bin
powershell.exe Start-Process "java '-jar lms_pc_installer.jar'" -verb RunAs