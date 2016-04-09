#!/bin/sh
#startdate=`date -d "yesterday" +"%m-%d-%Y-08-00-01"`
#enddate=`date -d "yesterday" +"%m-%d-%Y-18-59-59"`
#echo $startdate
startdate=10-12-2011-15-00-00
enddate=10-12-2011-15-01-00
dirname=`date --date 'yesterday' +%Y%m%d`
echo $dirname
#mkdir $dirname
#cd $dirname
mkdir peakworkloaddata 
cd  peakworkloaddata
while read inputline
do
  platform="$(echo $inputline | cut -d,  -f1|tr '/' '_')"
  role="$(echo $inputline | cut -d,  -f2)"
#  echo platform = $platform and role = $role
<api call to create files> MINUTE >"RX_"$role"__"$platform
sort "RX_"$role"__"$platform>"RX_"$role"__"$platform"_sorted"
rm "RX_"$role"__"$platform

<api call to create files>  MINUTE >"TX_"$role"__"$platform
sort "TX_"$role"__"$platform>"TX_"$role"__"$platform"_sorted"
rm "TX_"$role"__"$platform

<api call to create system metricfile>  MINUTE >"CPU_"$role"__"$platform
sort "CPU_"$role"__"$platform>"CPU_"$role"__"$platform"_sorted"
rm "CPU_"$role"__"$platform


<api call to create system metricfile>  MINUTE >"TOT_"$role"__"$platform
sort "TOT_"$role"__"$platform>"TOT_"$role"__"$platform"_sorted"
rm "TOT_"$role"__"$platform


done < /home/sdhingra/data_automation/platform_role_stats.txt

exit 0
