#!/bin/bash
#password is password2
curl -sL --connect-timeout 1 -i http://localhost:9090/secure/aa -H "Authorization: Basic dXNlcjpwYXNzd29yZDI="
echo ''
