<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
<title>Demand Services Monitoring</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
<link rel="stylesheet" href="table-fixed-header.css">
<script src="bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="table-fixed-header.js"></script>
<script type="text/javascript" src="sorting.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){$('.stopGapCondition').each(function() {
    var $this = $(this);
    var value = $this.text();
    if(!/^\s*\d+(\.\d+)?\s*$/.test(value)) {
        $this.addClass('notApplicable');
        return;
    }
    if(value < 41) {
        $this.addClass('green');
    } else if(value < 70) {
        $this.addClass('yellow');
    } else {
        $this.addClass('red');
    }
    $this.text($this.text());
});});
</script>
<!--<script language="javascript" type="text/javascript">
function myfunction(){
  if( typeof myfunction.cnt == 'undefined' ) {
        myfunction.cnt=0;
      }
        myfunction.cnt++;
        return myfunction.cnt ; 
}
</script>-->
<script language="javascript" type="text/javascript">
$(document).ready(function(){
    var now = new Date();
    var month = (now.getMonth() + 1);               
    var day = now.getDate() - 1;
    if (day===0){now.setDate(0);day=now.getDate();month=now.getMonth()+1;}
    if(month < 10) 
        month = "0" + month;
    if(day < 10) 
        day = "0" + day;
    var yesday = now.getFullYear() + '-' + month + '-' + day;
    <!--var mycnt = myfunction();-->
    if(!(document.location.search.length))
    {
    $('#datePicker').val(yesday);
    myform.submit();
    }
 });
</script>
<!--  style type="text/css">a {text-decoration: none}</style>
<style = "text/css">
.odd {	
 background-color: Aqua;
}
.even {
 background-color: #CCCCFF;
}
table {
	text-align: left;
        font-size: 20px;
	font-family: "Times New Roman";
	background: #c0c0c0;
      } 

div#myDiv {
    width: 85%;
    height: 700px;
    position: fixed;
    left: 40px;
    padding: 10px;
}
<table thead  {
	cursor: pointer;
	}
table thead tr,
table tfoot tr {
	background: #c0c0c0;
	}
 
table tbody tr {
	background: #f0f0f0;
	}
 
td,th {
	border: 1px solid white;
	}
td {padding: 5px;}
.red {
    background-color: #f99;
}
.yellow {
    background-color: #ff9;
}
.green {
    background-color: #9f9;
}
.notApplicable {
    background-color: #fff;
}
td.stopGapCondition {
    text-align: center;
}
.container {
  width: 94%;
  left: 150px;
  position: fixed;
}
.container-fluid {
  min-width: 100%;
}
</style-->
<style>
.table-content {
	width:100% !important
}
</style>
</head>
 <!--  >Title class="PageTitle">Welcome to metrics dashboard</Title>  
  <hr/>  
  <h3 style="color:green;font-family:Times New Roman,Georgia,Serif;background-color:#CCCCFF" align="center">Welcome to metrics dashboard</h3>  
 <hr/>  

<h3 style="color:green;font-family:Times New Roman,Georgia,Serif" align="center">Master Snapshot</h3-->
<body style="font-size:10px"	> 

<div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="#">Metrics Dashboard</a>
          
        </div>
      </div>
    </div>
    
    
        	
        	
     
	<div class="container-fluid" style="margin-top:50px">
	<div class="row-fluid">
	<div class="span12">
	<h4 style="text-align:center">Master Snapshot</h4>
	</div>
	<div class="row-fluid">
	<div class="span12">
	<div class="tabbable span12" style="height:100%;font-size:12px">
        		<ul class="nav nav-tabs" id="tabs" data-tabs="tabs">
              	<li><a href="/masterpage/cpu.do"> Role-Based View</a></li>
              	<li><a href="/topstats/topstats">Mesos-Primary</a></li>
            	</ul>
            	
        	</div><!-- tabbable -->
	</div>
      <div class="row-fluid">
        <div class="span2">
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
            
              <li class="nav-header">Utilization</li>
              <li class="nav-header">Master</li>
               <li><a href="http://localhost:8080/page_cpu/cpu.do">CPU</a></li>
               <li><a href="http://localhost:8080/page_tx/TxServlet" >Network_TX</a></li>
               <li><a href="http://localhost:8080/page_rx/RxServlet">Network_RX</a></li>
               <li class="nav-header">Trends</li>
               <li><a href="http://localhost:8080/mytrendapp/cpu.do">CPU</a></li>
               <li><a href="http://localhost:8080/myspread/cpu.do">Spread</a></li>
              <li><a href="http://localhost:8080/memtrendapp/mem.do">Memory</a></li>
              
            </ul>
          </div><!--/.well -->
        </div><!--/span-->
        <div class="span10">
        <form name="myform" method="GET" action="/masterpage/cpu.do"; "/>
<table  class = "datepicker"  align="center">
<tr>
<td><b>Date Picker</b></td>
<!-- <td bgcolor="Teal" > <input name = "datePicker" id="datePicker" type="date"></td> -->
<%  
String strMyText ="";  
if(request.getParameter("datePicker")!=null)  
{  
strMyText = request.getParameter("datePicker");  
}
%>
<td> &nbsp;<input name = "datePicker" id="datePicker" style="font-size:10px" type="date" value="<%= strMyText%>"></td>
<td> &nbsp;<input type="submit" id="mybutton" value="Submit" style="font-size:10px" name="sub" /"> </td>
</tr>
</table>
</form>

<div class="container-fluid" >
<div class="row-fluid fixed-table" style="overflow-x:auto;">
<div class="table-content">
<table border="1"  class="table table-striped table-fixed-header" id="sortable_example"  align="center">
<thead class="header">
<tr>
<th ><a onclick="SortTable(0);" href="javascript:;"><b>Role</b></a></th>
<th ><a onclick="SortTable(1);" href="javascript:;"><b>Platform</b></a></th>
<th ><a onclick="SortTable(2);" href="javascript:;"><b>&nbsp;Cores&nbsp;</b></a></th>
<th ><a onclick="SortTable(3);" href="javascript:;"><b>&nbsp;Cpu&nbsp;</b></a></th>
<th ><a onclick="SortTable(4);" href="javascript:;"><b>&nbsp;CpuSprd&nbsp;&nbsp;</b></a></th>
<th ><a onclick="SortTable(5);" href="javascript:;"><b>&nbsp;&nbsp;Load&nbsp;&nbsp;</b></a></th>
<th ><a onclick="SortTable(6);" href="javascript:;"><b>&nbsp;&nbsp;LoadSprd&nbsp;&nbsp;</b></a></th>
<th ><a onclick="SortTable(7);" href="javascript:;"><b>&nbsp;&nbsp;Memory%&nbsp;&nbsp;</b></a></th>
<th ><a onclick="SortTable(8);" href="javascript:;"><b>&nbsp;&nbsp;Disk%&nbsp;&nbsp;</b></a></th>
<th ><a onclick="SortTable(9);" href="javascript:;"><b>&nbsp;&nbsp;Tx(Mbps)&nbsp;&nbsp;</b></a></th>
<th  ><a onclick="SortTable(10);" href="javascript:;"><b>&nbsp;&nbsp;Rx(Mbps)&nbsp;&nbsp;</b></a></th>
<th ><a onclick="SortTable(11);" href="javascript:;"><b>&nbsp;&nbsp;Tx(Kpps)&nbsp;&nbsp;</b></a></th>
<th ><a onclick="SortTable(12);" href="javascript:;"><b>&nbsp;&nbsp;Rx(Kpps)&nbsp;&nbsp;</b></a></th>
<!--<th width="100" ><a onclick="SortTable(13);" href="javascript:;"><b>&nbsp;&nbsp;&nbsp;&nbsp;Reads/s&nbsp;&nbsp;</b></a></th>
<th width="100" ><a onclick="SortTable(14);" href="javascript:;"><b>&nbsp;&nbsp;&nbsp;&nbsp;Writes/s&nbsp;&nbsp;</b></a></th>
<th width="100" ><a onclick="SortTable(15);" href="javascript:;"><b>&nbsp;&nbsp;Time_reads(ms)&nbsp;&nbsp;</b></a></th>
<th width="100" ><a onclick="SortTable(16);" href="javascript:;"><b>&nbsp;&nbsp;Time_writes(ms)&nbsp;&nbsp;</b></a></th -->
</tr>
</thead>
<tbody>
        <c:forEach var="cpu" items="${metricList}">
            <tr>
                <td>${cpu.role}</td>
                <td>${cpu.platform}</td>
                <td><c:choose><c:when test="${cpu.count < 0}">TimedOut</c:when><c:otherwise>${cpu.count}</c:otherwise></c:choose></td>
                <td><c:choose><c:when test="${cpu.perc < 0}">TimedOut</c:when><c:otherwise>${cpu.perc}</c:otherwise></c:choose></td>
                <td><c:choose><c:when test="${cpu.spread < 0}">TimedOut</c:when><c:otherwise>${cpu.spread}</c:otherwise></c:choose></td>
                <td><c:choose><c:when test="${cpu.load1 < 0}">TimedOut</c:when><c:otherwise>${cpu.load1}</c:otherwise></c:choose></td>
                <td><c:choose><c:when test="${cpu.l_spread < 0}">TimedOut</c:when><c:otherwise>${cpu.l_spread}</c:otherwise></c:choose></td>
                <td><c:choose><c:when test="${cpu.mem_p95th < 0}">TimedOut</c:when><c:otherwise>${cpu.mem_p95th}</c:otherwise></c:choose></td>
                <td><c:choose><c:when test="${cpu.disk_util < 0}">TimedOut</c:when><c:otherwise>${cpu.disk_util}</c:otherwise></c:choose></td>
                <td><c:choose><c:when test="${cpu.tx_p95th < 0}">TimedOut</c:when><c:otherwise>${cpu.tx_p95th}</c:otherwise></c:choose></td>
                <td><c:choose><c:when test="${cpu.rx_p95th < 0}">TimedOut</c:when><c:otherwise>${cpu.rx_p95th}</c:otherwise></c:choose></td>
                <td><c:choose><c:when test="${cpu.txpp95th < 0}">TimedOut</c:when><c:otherwise>${cpu.txpp95th}</c:otherwise></c:choose></td>
                <td><c:choose><c:when test="${cpu.rxpp95th < 0}">TimedOut </c:when><c:otherwise>${cpu.rxpp95th}</c:otherwise></c:choose></td>
<!--                <td><c:choose><c:when test="${cpu.num_reads < 0}">TimedOut</c:when><c:otherwise>${cpu.num_reads}</c:otherwise></c:choose></td>
                <td><c:choose><c:when test="${cpu.num_writes < 0}">TimedOut </c:when><c:otherwise>${cpu.num_writes}</c:otherwise></c:choose></td>
                <td><c:choose><c:when test="${cpu.time_reads < 0}">TimedOut</c:when><c:otherwise>${cpu.time_reads}</c:otherwise></c:choose></td>
                <td><c:choose><c:when test="${cpu.time_writes < 0}">TimedOut </c:when><c:otherwise>${cpu.time_writes}</c:otherwise></c:choose></td>  -->
            </tr>
        </c:forEach> 
   </tbody>
  </table>
  </div>
     <script language="javascript" type="text/javascript" >
    $(document).ready(function(){
      // add 30 more rows to the table
      var row = $('table#sortable_example > tbody > tr:first');
      for (i=0; i<0; i++) {
        $('table#sortable_example > tbody').append(row.clone());
      }

      // make the header fixed on scroll
      $('.table-fixed-header').fixedHeader();
    });
  </script>
  </div>
 
    <%--For displaying Previous link except for the 1st page --%>
    <c:if test="${currentPage != 1}">
        <td><a href="cpu.do?page=${currentPage - 1}">Previous</a></td>
    </c:if>
 
    <%--For displaying Page numbers.
    The when condition does not display a link for the current page--%>
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="cpu.do?page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>
    <%--For displaying Next link --%>
    <c:if test="${currentPage lt noOfPages}">
        <td><a href="cpu.do?page=${currentPage + 1}">Next</a></td>
    </c:if>
  </div>
</div> 

        
        
        </div>
        </div>
        </div>
       
 

</body>
</html>
