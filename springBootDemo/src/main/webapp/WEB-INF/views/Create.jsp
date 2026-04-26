<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring Store</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
}

.header {
	background-color: #0F1111;
	color: white;
	padding: 15px;
	font-size: 32px;
	font-weight: bold;
	text-align: left;
}

.container {
	padding: 20px;
	text-align: center;
}

.title {
	font-size: 28px;
	font-weight: bold;
	margin-bottom: 20px;
}

.grid-container {
	display: flex;
	flex-direction: column;
	align-items: center;
	margin-bottom: 40px;
}

.grid {
	display: grid;
	grid-template-columns: repeat(3, 1fr);
	gap: 20px;
	justify-content: center;
	border: 1px solid #ccc;
	padding: 15px;
	border-radius: 5px;
	background-color: #fff;
	width: 80%;
	max-width: 1000px;
}

.book {
	padding: 10px;
	border: 1px solid #ddd;
	border-radius: 5px;
	background-color: #f9f9f9;
}

.rating {
	font-size: 14px;
	color: #555;
}
</style>
</head>
<body>
	<div class="header">Spring Store</div>
	<div class="container">
		<div class="grid-container">
			<div class="title">Add new Book</div>
			<form class="grid" method = "post" action="book">
				<div class="book">
				<label for="title">Title:</label><br>
			  <input type="text" id="title" name="title"><br>
			  <label for="amazonRating">Amazon Rating:</label><br>
			  <input type="text" id="rating" name="rating">
			    </div>
			    <input type="submit" value="Submit" id="title">
			</form>
		</div>
	</div>
</body>
</html>