<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Books Sale</title>
</head>
<body>
<table>
<c:forEach var = "book" items="${myBooks}">
<tr>
<td> Title <span style="color: #B13100;">${book.title}</span></td>
<td>  By <span style="color: #B13100;">${book.author}</span> </td>
<td>  Rating <span style="color: #B13100;">${book.rating}</span> </td>
</tr>
 <tr>
     <td>&nbsp;</td>
  </tr>
</c:forEach> 
</table>
</body>
</html>