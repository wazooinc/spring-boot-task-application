import React, {useState, useEffect} from 'react';
import {useParams} from 'react-router-dom';
import {createTask, getTask, updateTask } from './services';

const Task = () => {
    const [data, setData] = useState({});
    const [description, setDescription] = useState('');
    const [status, setStatus] = useState('');
    const params = useParams();

    const handleStatus = (event) => {
        setStatus(event.target.value);
    }

    const handleDescription = (event) => {
        setDescription(event.target.value);
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        let options = data;
        options.status = status;
        options.description = description;

        if (options.id) {
            updateTask(options.id, options)
                .then(response => {
                    setData(response);
                });
        } else {
            createTask(options)
                .then(response => {
                    setData(response);
                });
        }
    }

    useEffect(() => {
        if (params?.id === 'new') return;

        getTask(params.id)
            .then(item => {
                setData(item)
            })
    }, [params]);

    useEffect(() => {
        if (data) {
            setDescription(data.description);
            setStatus(data.status);
        }
    }, [data]);

    return (
        <>
            <div className="container">
                <div className="task">
                    <form onSubmit={handleSubmit}>
                        <div className="form-group">
                            <label htmlFor="description">Description</label>
                            <input className="form-control"
                                   type="text"
                                   id="description"
                                   value={description}
                                   onChange={handleDescription}
                            />
                        </div>

                        <div className="form-group">
                            <label htmlFor="status">Status</label>
                            <input className="form-control"
                                   type="text"
                                   id="status"
                                   value={status}
                                   onChange={handleStatus}
                            />
                        </div>

                        <button type="submit" className="btn btn-primary">Update</button>
                    </form>
                </div>
            </div>
        </>
    )
}

export default Task;