import 'bootstrap/dist/css/bootstrap.min.css';
import {Routes, Route} from 'react-router-dom';
import Home from './Home';
import Navbar from './Navbar';
import Task from './Task';

const App = () => {
  return (
    <>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/tasks/:id" element={<Task />} />
      </Routes>
    </>
  );
}

export default App;
