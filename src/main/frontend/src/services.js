
export function getTasks() {
    return fetch('/api/v1/tasks')
        .then(data => data.json());
}

export function getTask(id) {
    return fetch(`/api/v1/tasks/${id}`)
        .then(data => data.json());
}

export function updateTask(id, data) {
    const options = {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(data)
    };

    return fetch(`/api/v1/tasks/${id}`, options)
        .then(data => data.json());
}

export function createTask(data) {
    const options = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(data)
    }

    return fetch(`/api/v1/tasks`, options)
        .then(data => data.json());
}