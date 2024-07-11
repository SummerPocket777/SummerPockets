export function handlePromise(promiseFunction, data) {
	return new Promise((resolve, reject) => {
		promiseFunction(data)
			.then(response => {
				resolve(response);
			})
			.catch(error => {
				console.error("Error:", error);
				reject(error);
			});
	});
}