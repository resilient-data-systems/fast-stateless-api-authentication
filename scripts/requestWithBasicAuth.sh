#!/bin/bash
#password is password
curl -sL --connect-timeout 1 -i http://localhost:9090/secure/aa -H "Authorization: Basic dXNlcjpwYXNzd29yZA=="
echo ''
