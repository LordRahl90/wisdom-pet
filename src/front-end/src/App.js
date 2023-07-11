import React from 'react';
import {BrowserRouter,Routes,Route} from 'react-router-dom';
import Layout from './pages/Layout';
import Home from './pages/Home';
import './App.css';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
            <Route path="/" element={<Layout />}>
                <Route index element={<Home />} />
            </Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
