var sortedOn = 0; 
  
function SortTable(sortOn) {  
var table = document.getElementById('sortable_example');  
var tbody = table.getElementsByTagName('tbody')[0];  
var rows = tbody.getElementsByTagName('tr');  
var rowArray = new Array();  
for (var i=0, length=rows.length; i<length; i++) {  
rowArray[i] = new Object;  
rowArray[i].oldIndex = i;  
rowArray[i].value = rows[i].getElementsByTagName('td')[sortOn].firstChild.nodeValue;  
}  
  
if (sortOn == sortedOn) { rowArray.reverse(); }  
else {  
sortedOn = sortOn;  
/* 
Decide which function to use from the three:RowCompareNumbers, 
RowCompareDollars or RowCompare (default). 
For first column, I needed numeric comparison. 
*/  
if (sortedOn == 0 || sortedOn == 1 ) {  
rowArray.sort(RowCompare);  
} else
{
rowArray.sort(RowCompareNumbers);  
} 

//if (sortedOn == 2) {  
//rowArray.sort(RowCompareNumbers);  
//}
//if (sortedOn == 3 || sortedOn == 4 || sortedOn == 5 || sortedOn == 6 || sortedOn == 7 || sortedOn == 8 || sortedOn == 9 || sortedOn == 10 || sortedOn == 11 || sortedOn == 12) {  
  // rowArray.sort(RowCompareNumbers);
//}
}
var newTbody = document.createElement('tbody');  
for (var i=0, length=rowArray.length ; i<length; i++) {  
newTbody.appendChild(rows[rowArray[i].oldIndex].cloneNode(true));  
}  
  
table.replaceChild(newTbody, tbody);  
}  
  
function RowCompare(a, b) {   
var aVal = a.value;  
var bVal = b.value;  
return (aVal == bVal ? 0 : (aVal > bVal ? 1 : -1));  
}  

// Compare number  
function RowCompareNumbers(a, b) {  
var aVal = parseInt(a.value);  
var bVal = parseInt(b.value);  
return (aVal - bVal);  
}  

// Compare float  
function RowCompareFloats(a, b) {  
var aVal = parseFloat(a.value);  
var bVal = parseFloat(b.value);  
return (aVal - bVal); 
} 
