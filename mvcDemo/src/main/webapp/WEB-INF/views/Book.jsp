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
			<div class="title">Book info</div>
			<div class="grid">
				<div class="book">
				<div class="id">ID: ${book.id}</div>
				<div class="title">Title: ${book.title}</div>
				<div class="rating">Rating: ${book.amazonRating}</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>