#!/bin/bash
mv /var/lib/jira/import/attachments/SOLSETWEBOPS /var/lib/jira/import/attachments/WEBOPS
cd /var/lib/jira/import/attachments/WEBOPS 
for dir in $(ls -1d SOLSETWEBOPS*); do num=`echo $dir | cut -d '-' -f 2`; mv $dir WEBOPS-$num; done