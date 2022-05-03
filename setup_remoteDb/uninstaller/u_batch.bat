copy "C:\Program Files\lms_pc\sb\hosts" "C:\Windows\System32\drivers\etc\hosts"
rmdir /Q /S "C:\Program Files\lms_pc"
rmdir /Q /S "C:\Users\Public\lms_pc\logs"
rmdir /Q /S "C:\Users\Public\lms_pc"
del "C:\ProgramData\Microsoft\Windows\Start Menu\Programs\StartUp\start_pb.lnk"
del "C:\ProgramData\Microsoft\Windows\Start Menu\Programs\StartUp\starter.lnk"
echo "Please restart your pc for completion of uninstallation process..."
pause