#! /usr/bin/bash
#ask for information, use exec < dev/tty to allow prompt in post-commit
#dont forget to ensure the EOL aligns with unix specification: dos2unix <filename>
read -p "Enter Comment:" commentVar

dateVar=$(date +%Y%m%d)
fullName="${commentVar}${dateVar}"


zip -r outputs/$fullName.zip ./ -x ./*.zip