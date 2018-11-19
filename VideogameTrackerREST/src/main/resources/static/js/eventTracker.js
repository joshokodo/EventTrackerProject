window.addEventListener('load', function(e) {
	const form = document.form;
	const stats = document.getElementById('stats');
	const gameDiv = document.getElementById('gameData');

	
	const titleButtonLabel = 'Sort By Title';
	const ownedButtonLabel = 'Sort By Owned';
	const dateButtonLabel = 'Sort By Release Date';

	const byTitleButton = createButton('byTitle', titleButtonLabel);
	const byOwnedButton = createButton('byOwned', ownedButtonLabel);
	const byDateButton = createButton('byDate', dateButtonLabel);

	var byTitlePath = 'api/games';
	var byOwnedPath = 'api/games';
	var byDatePath = 'api/games';

	console.log('document loaded');
	init();

	function init() {
		getAllGames();
		form.button.addEventListener('click', addGameCallback);
	}

	function createButton(buttonName, buttonText) {
		button = document.createElement('button');
		button.name = buttonName;
		button.textContent = buttonText;
		return button;
	}

	function clearChildren(element) {

		while (element.firstChild) {
			element.removeChild(element.firstChild);
		}
	}

	function gamesByTitleCallback(e) {
		e.preventDefault();

		byOwnedButton.textContent = ownedButtonLabel;
		byOwnedPath = 'api/games';

		byDateButton.textContent = dateButtonLabel;
		byDatePath = 'api/games';

		if (byTitlePath === 'api/games') {
			e.target.textContent = titleButtonLabel + '\u25B2';
			byTitlePath = 'api/games/sort/title/ascend';
		} else if (byTitlePath === 'api/games/sort/title/ascend') {
			e.target.textContent = titleButtonLabel + '\u25BC';
			byTitlePath = 'api/games/sort/title/descend';
		} else if (byTitlePath === 'api/games/sort/title/descend') {
			e.target.textContent = titleButtonLabel;
			byTitlePath = 'api/games';
		}
		getAllGamesBySortPath(byTitlePath);
	}

	function gamesByOwnedCallback(e) {
		e.preventDefault();

		byTitleButton.textContent = titleButtonLabel;
		byTitlePath = 'api/games';

		byDateButton.textContent = dateButtonLabel;
		byDatePath = 'api/games';

		if (byOwnedPath === 'api/games') {
			e.target.textContent = ownedButtonLabel + '\u25B2';
			byOwnedPath = 'api/games/sort/own/ascend';
		} else if (byOwnedPath === 'api/games/sort/own/ascend') {
			e.target.textContent = ownedButtonLabel + '\u25BC';
			byOwnedPath = 'api/games/sort/own/descend';
		} else if (byOwnedPath === 'api/games/sort/own/descend') {
			e.target.textContent = ownedButtonLabel;
			byOwnedPath = 'api/games';
		}
		getAllGamesBySortPath(byOwnedPath);
	}

	function gamesByDateCallback(e) {
		e.preventDefault();

		byTitleButton.textContent = titleButtonLabel;
		byTitlePath = 'api/games';

		byOwnedButton.textContent = ownedButtonLabel;
		byOwnedPath = 'api/games';

		if (byDatePath === 'api/games') {
			e.target.textContent = dateButtonLabel + '\u25B2';
			byDatePath = 'api/games/sort/releaseDate/ascend';
		} else if (byDatePath === 'api/games/sort/releaseDate/ascend') {
			e.target.textContent = dateButtonLabel + '\u25BC';
			byDatePath = 'api/games/sort/releaseDate/descend';
		} else if (byDatePath === 'api/games/sort/releaseDate/descend') {
			e.target.textContent = dateButtonLabel;
			byDatePath = 'api/games';
		}
		getAllGamesBySortPath(byDatePath);
	}

	function addGameCallback(e) {
		e.preventDefault();
		let plats = form.elements['platforms[]'];
		let game = {
			 title : form.title.value,
			 description : form.description.value,
			 features : form.features.value,
			 price : form.price.value,
			 own : form.own.value,
			 releaseDate : form.releaseDate.value,
			 rating : buildRating(form.rating.value),
			 category : buildCategory(form.category.value),
			 platforms : buildPlatforms(plats)

		}
		addGame(game);
	};
	function updateGameCallback(e) {
		e.preventDefault();
		let plats = form.elements['platforms[]'];
		let game = {
			 id : form.id.value,
			 title : form.title.value,
			 description : form.description.value,
			 features : form.features.value,
			 price : form.price.value,
			 own : form.own.value,
			 releaseDate : form.releaseDate.value,
			 rating : buildRating(form.rating.value),
			 category : buildCategory(form.category.value),
			 platforms : buildPlatforms(plats)

		}
		updateGame(game);
	};
	

	function addGame(game) {
		let xhr = new XMLHttpRequest();
		xhr.open('POST', 'api/games/');
		xhr.setRequestHeader("Content-type", "application/json");

		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4) {
				if (xhr.status < 400) {
					let response = xhr.responseText;
					let gameParsed = JSON.parse(response);
					displayGame(gameParsed);

				} else {

					let failHeader = document.createElement('h1');
					failHeader.textContent = 'No Game Data Found';
					gameDiv.appendChild(failHeader);
				}
			}
		}

		xhr.send(JSON.stringify(game));
	}
	
	function deleteGame(id) {
		let xhr = new XMLHttpRequest();
		xhr.open('DELETE', 'api/games/' + id);

		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4) {
				if (xhr.status < 400) {
					let response = xhr.responseText;
					let game = JSON.parse(response);
					getAllGames();

				} else {

					let failHeader = document.createElement('h1');
					failHeader.textContent = 'No Game Data Found';
					gameDiv.appendChild(failHeader);
				}
			}
		}
		xhr.send();
	}
	
	function updateGame(game){
		let xhr = new XMLHttpRequest();
		xhr.open('PUT', 'api/games/' + game.id);
		xhr.setRequestHeader("Content-type", "application/json");

		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4) {
				if (xhr.status < 400) {
					let response = xhr.responseText;
					let gameParsed = JSON.parse(response);
					displayGame(gameParsed);

				} else {

					let failHeader = document.createElement('h1');
					failHeader.textContent = 'No Game Data Found';
					gameDiv.appendChild(failHeader);
				}
			}
		}
		console.log(game);
		xhr.send(JSON.stringify(game));
	}


	function getGame(id) {
		let xhr = new XMLHttpRequest();
		xhr.open('GET', 'api/games/' + id);

		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4) {
				if (xhr.status < 400) {
					let response = xhr.responseText;
					let game = JSON.parse(response);
					displayGame(game);

				} else {

					let failHeader = document.createElement('h1');
					failHeader.textContent = 'No Game Data Found';
					gameDiv.appendChild(failHeader);
				}
			}
		}
		xhr.send();
	}
	
	function getAllGames() {
		let xhr = new XMLHttpRequest();
		xhr.open('GET', 'api/games');

		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4) {
				if (xhr.status < 400) {
					let response = xhr.responseText;
					let games = JSON.parse(response);
					displayGames(games);

				} else {

					let failHeader = document.createElement('h1');
					failHeader.textContent = 'No Game Data Found';
					gameDiv.appendChild(failHeader);
				}
			}
		}
		xhr.send();
	}

	function getAllGamesBySortPath(path) {
		let xhr = new XMLHttpRequest();
		xhr.open('GET', path);

		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4) {
				if (xhr.status < 400) {
					let response = xhr.responseText;
					let games = JSON.parse(response);
					displayGames(games);

				} else {

					let failHeader = document.createElement('h1');
					failHeader.textContent = 'No Game Data Found';
					gameDiv.appendChild(failHeader);
				}
			}
		}
		xhr.send();
	}

	function displayGames(games) {
		
		form.reset();
		
		form.button.textContent = 'Add Game';
		form.button.removeEventListener('click', updateGameCallback);
		form.button.addEventListener('click', addGameCallback);

		clearChildren(gameDiv);
		displayStats(games);

		let table = document.createElement('table');
		let tableBody = document.createElement('tbody');
		let tableHead = document.createElement('thead');

		let r = document.createElement('tr');
		let titleButton = document.createElement('th');
		let ownedButton = document.createElement('th');
		let dateButton = document.createElement('th');

		byTitleButton.addEventListener('click', gamesByTitleCallback);
		byOwnedButton.addEventListener('click', gamesByOwnedCallback);
		byDateButton.addEventListener('click', gamesByDateCallback);

		titleButton.appendChild(byTitleButton);
		ownedButton.appendChild(byOwnedButton);
		dateButton.appendChild(byDateButton);

		r.appendChild(titleButton);
		r.appendChild(ownedButton);
		r.appendChild(dateButton);

		tableHead.appendChild(r);
		table.appendChild(tableHead)
		table.appendChild(tableBody);

		if (Array.isArray(games)) {
			games.forEach(function(game) {

				let row = document.createElement('tr');
				let title = document.createElement('td');
				let owned = document.createElement('td');
				let releaseDate = document.createElement('td');

				title.textContent = game.title;
				row.addEventListener('click', function() {
					getGame(game.id);
				});

				if (game.own) {
					owned.textContent = 'I Own This Game';
					owned.style.color = 'lightGreen';
				} else {
					owned.textContent = 'I Don\'t Own This Game';
					owned.style.color = 'red';
				}

				releaseDate.textContent = 'Release Date: '
						+ game.releaseDate.toString().slice(0, 10);

				if (new Date(game.releaseDate) >= new Date()) {
					releaseDate.style.color = 'red';
				} else {
					releaseDate.style.color = 'lightGreen';
				}

				row.appendChild(title);
				row.appendChild(owned);
				row.appendChild(releaseDate);
				tableBody.appendChild(row);

			});
			gameDiv.appendChild(table);
		}
	}

	function displayGame(game) {
		form.reset();
		updateForm(game);
		clearChildren(stats);
		form.id.value = game.id;
		
		form.button.textContent = 'Update Game';
		form.button.removeEventListener('click', addGameCallback);
		form.button.addEventListener('click', updateGameCallback);
		
		clearChildren(gameDiv);
		let deleteButton = document.createElement('button');
		let allGamesButton = document.createElement('button');
		
		let title = document.createElement('h1');
		title.textContent = game.title;
		
		let id = document.createElement('h1');
		id.textContent = game.id;
		
		deleteButton.textContent = 'delete game';
		deleteButton.addEventListener('click', function(e){
			deleteGame(game.id);
		});
		
		allGamesButton.textContent = 'all Games';
		allGamesButton.addEventListener('click', function(e){
			getAllGames();
		});
		
		
		gameDiv.appendChild(deleteButton);
		gameDiv.appendChild(allGamesButton);
		gameDiv.appendChild(title);
		gameDiv.appendChild(id);
		let list = document.createElement('ul');
		
		
		let description = document.createElement('li');
		description.textContent ='description: ' + game.description;
		list.appendChild(description);
		
		let features = document.createElement('li');
		features.textContent ='features: ' +  game.features;
		list.appendChild(features);
		
		let price = document.createElement('li');
		price.textContent ='price: ' + game.price;
		list.appendChild(price);
		
		let own = document.createElement('li');
		own.textContent = 'own Game: ' + game.own;
		list.appendChild(own);
		
		let date = document.createElement('li');
		date.textContent = 'releaseDate: ' + game.releaseDate.toString().slice(0,10);
		list.appendChild(date);
		
		let rating = document.createElement('li');
		rating.textContent = 'rated: ' + game.rating.rated;
		list.appendChild(rating);
		
		let category = document.createElement('li');
		category.textContent = 'category: ' + game.category.gameType;
		list.appendChild(category);
		
		game.platforms.forEach(function(value){
			let platform = document.createElement('li');
			platform.textContent = value.gameSystem;
			list.appendChild(platform);
			
		});
		gameDiv.appendChild(list);
		
		
	}
	
	function displayStats(games){
		clearChildren(stats);
		let stat1 = document.createElement('h1');
		let stat2 = document.createElement('h1');
		let ownCount = 0;
		let wishlistPriceTotal = 0;
		for(let i = 0; i < games.length; i++){
			if(games[i].own){
				ownCount++;
			}
			else{
				wishlistPriceTotal = wishlistPriceTotal + games[i].price;
			}
		}
		stat1.textContent = "I own " + ownCount + " out of " + games.length + " games";
		stat2.textContent = "Money needed to get all wishlist games: $" + wishlistPriceTotal;
		stats.appendChild(stat1);
		stats.appendChild(stat2);
		
	}
	
	function updateForm(game){
		form.title.value = game.title;
		form.description.value = game.description;
		form.features.value = game.features;
		form.features.value = game.features;
		form.price.value = game.prices;
		form.releaseDate.value = game.releaseDate;
		
		
	}
	
	
	function buildCategory(category){
		cat = {};
		cat.id = undefined;
		cat.gameType = category;
		switch(category){
		case 'ACTION':
			cat.id = 1;
			break;
			
		case 'ADVENTURE':
			cat.id = 2;
			break;
			
		case 'PUZZLE':
			cat.id = 3;
			break;
			
		case 'RPG':
			cat.id = 4;
			break;
			
		case 'SHOOTER':
			cat.id = 5;
			break;
			
		case 'SPORTS':
			cat.id = 6;
			break;
			
		case 'FIGHTING':
			cat.id = 7;
			break;
			
		}
		return cat;
	}
	
	function buildRating(rating){
		rat = {};
		rat.id = undefined;
		rat.rated = rating;
		switch(rating){
		case 'E':
			rat.id = 1;
			
		case 'E10UP':
			rat.id = 2;
			
		case 'T':
			rat.id = 3;
			
		case 'M':
			rat.id = 4;
			
		case 'A':
			rat.id = 5;
			
		case 'TBD':
			rat.id = 6;
	
			
		}
		return rat;
	}
	function buildPlatforms(plats){
		platforms = [];
		
		plats.forEach(function(system, index){
			if(system.checked){
				platforms.push({
					id : index + 1,
					gameSystem : system.value
				});
			}
		});
		return platforms;
	}
	
	function toggleButton(){
		if(form.button.textContent === 'Add Game'){
			form.button.textContent = 'Update Game';
			form.button.removeEventListener('click', addGameCallback);
			form.button.addEventListener('click', updateGameCallback);
			
		}
		else if(form.button.textContent === 'Update Game'){
			form.button.textContent = 'Add Game';
			form.button.removeEventListener('click', updateGameCallback);
			form.button.addEventListener('click', addGameCallback);
		}
	}
	
	function verifyForm(title, price, date, rating, category, systems){
		  var errorsText = [];
		  if(title === ''){
		    errorsText.push('title can\'t be blank');
		  }
		  if(price === undefined){
		    errorsText.push('price can\'t be blank');
		  }
		 
		  if(date === ''){
		    errorsText.push('release date can\'t be blank');
		  }
		  else if(parseInt(zip).toString().length !== 5){
		    errorsText.push('zip must be 5 digits');
		    console.log('8');
		  }
		  return errorsText;
	}

}); // end of on load
