window.addEventListener('load', function(e) {
	const form = document.mainForm;
	const gameDiv = document.getElementById('gameData');
	const titleButtonLabel = 'Sort By Title';
	const ownedButtonLabel = 'Sort By Owned';
	const dateButtonLabel = 'Sort By Release Date';
	
	var byTitlePath = 'api/games';
	var byOwnedPath = 'api/games';
	var byDatePath = 'api/games';

	console.log('document loaded');
	init();

	function init() {
		
		formInit();
		getAllGames();
		
		form.byTitle.addEventListener('click', gamesByTitleCallback);
		form.byOwned.addEventListener('click', gamesByOwnedCallback);
		form.byDate.addEventListener('click', gamesByDateCallback);
	}

	function formInit() {


		let byTitleButton = createButton('byTitle', titleButtonLabel);

		let byOwnedButton = createButton('byOwned', ownedButtonLabel);

		let byDateButton = createButton('byDate', dateButtonLabel);

		let createGameButton = createButton('create', 'Add Game');

		form.appendChild(byTitleButton);
		form.appendChild(byOwnedButton);
		form.appendChild(byDateButton);
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
			element.removeChild(element.firstChild);
		}
	}

	function gamesByTitleCallback(e) {
		e.preventDefault();
		
		form.byOwned.textContent = ownedButtonLabel;
		byOwnedPath = 'api/games';
		
		form.byDate.textContent = dateButtonLabel;
		byDatePath = 'api/games';
		
		if(byTitlePath === 'api/games'){
			e.target.textContent =  titleButtonLabel + '\u25B2' ;
			byTitlePath = 'api/games/sort/title/ascend';
		}
		else if(byTitlePath === 'api/games/sort/title/ascend'){
			e.target.textContent = titleButtonLabel + '\u25BC';
			byTitlePath = 'api/games/sort/title/descend';
		}
		else if(byTitlePath === 'api/games/sort/title/descend'){
			e.target.textContent = titleButtonLabel;
			byTitlePath = 'api/games';
		}
		getAllGamesBySortPath(byTitlePath);
	}
	
	function gamesByOwnedCallback(e) {
		e.preventDefault();
		
		form.byTitle.textContent = titleButtonLabel;
		byTitlePath = 'api/games';
		
		form.byDate.textContent = dateButtonLabel;
		byDatePath = 'api/games';
		
		if(byOwnedPath === 'api/games'){
			e.target.textContent = ownedButtonLabel + '\u25B2' ;
			byOwnedPath = 'api/games/sort/own/ascend';
		}
		else if(byOwnedPath === 'api/games/sort/own/ascend'){
			e.target.textContent = ownedButtonLabel + '\u25BC';
			byOwnedPath = 'api/games/sort/own/descend';
		}
		else if(byOwnedPath === 'api/games/sort/own/descend'){
			e.target.textContent = ownedButtonLabel;
			byOwnedPath = 'api/games';
		}
		getAllGamesBySortPath(byOwnedPath);
	}
	
	function gamesByDateCallback(e) {
		e.preventDefault();
		
		form.byTitle.textContent = titleButtonLabel;
		byTitlePath = 'api/games';
		
		form.byOwned.textContent = ownedButtonLabel;
		byOwnedPath = 'api/games';
		
		if(byDatePath === 'api/games'){
			e.target.textContent = dateButtonLabel + '\u25B2' ;
			byDatePath = 'api/games/sort/releaseDate/ascend';
		}
		else if(byDatePath === 'api/games/sort/releaseDate/ascend'){
			e.target.textContent = dateButtonLabel + '\u25BC';
			byDatePath = 'api/games/sort/releaseDate/descend';
		}
		else if(byDatePath === 'api/games/sort/releaseDate/descend'){
			e.target.textContent = dateButtonLabel;
			byDatePath = 'api/games';
		}
		getAllGamesBySortPath(byDatePath);
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

}); // end of on load
