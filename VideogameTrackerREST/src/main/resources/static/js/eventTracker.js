window.addEventListener('load', function(e) {
	const form = document.mainForm;
	const gameDiv = document.getElementById('gameData');

	console.log('document loaded');
	init();

	function init() {
		formInit();
		form.all.addEventListener('click', allGamesCallback);
	}

	function formInit() {

		let allGamesButton = createButton('all', 'All Games');

		let ownedGamesButton = createButton('owned', 'Owned Games');

		let wishlistGamesButton = createButton('wishlist', 'Wishlist Games');

		let createGameButton = createButton('create', 'Add Game');

		form.appendChild(allGamesButton);
		form.appendChild(ownedGamesButton);
		form.appendChild(wishlistGamesButton);
		form.appendChild(createGameButton);

	}

	function createButton(buttonName, buttonText) {
		button = document.createElement('button');
		button.name = buttonName;
		button.textContent = buttonText;
		return button;
	}

	function clearChildren(element) {

		while (element.firstChild) {
			element.removeChild(form.firstChild);
		}
	}

	function allGamesCallback(e) {
		e.preventDefault();
		getAllGames();
	}

	function displayGames(games) {
		clearChildren(gameDiv);

		let table = document.createElement('table');
		let tableBody = document.createElement('tbody');
		table.appendChild(tableBody);

		if (Array.isArray(games)) {
			games.forEach(function(game) {

				let row = document.createElement('tr');
				let title = document.createElement('td');
				let owned = document.createElement('td');
				let releaseDate = document.createElement('td');

				title.textContent = game.title;

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

}); // end of on load
