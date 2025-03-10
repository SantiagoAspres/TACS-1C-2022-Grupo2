import React, { useCallback, useEffect, useState } from 'react';
import logo from './logo.svg';
import './App.css';
import 'react-bootstrap-typeahead/css/Typeahead.css';
import 'react-toastify/dist/ReactToastify.css';

import {Navbar, Nav, NavDropdown, Container} from 'react-bootstrap';
import { isAuthenticated, logOut } from './services/authService';
import {  Routes,  Route, NavLink, useLocation, useNavigate} from "react-router-dom";

import LogIn from './pages/login/index';
import Home from './pages/home/index';
import Dictionary from './pages/dictionary/index';
import Helper from './pages/helper';
import Results from './pages/results';
import PublicTournaments from './pages/tournaments/publicTournaments';
import MyTournaments from './pages/tournaments/myTournaments';
import Tournament from './pages/tournaments/tournament';
import Positions from './pages/positions';
import ErrorPage from './pages/error';

export function App (){
  
  let location = useLocation();
  let navigate = useNavigate();

  const [auth, setAuth] = useState(isAuthenticated());
  
  const redirectOnNotAuth = () => {
    const authenticaded = isAuthenticated();
    setAuth(authenticaded);
    if(!authenticaded && location.pathname !== 'log-in'){
      navigate('log-in');
    }
  };

  useEffect(() => {
    redirectOnNotAuth();
  }, [])

  useEffect(() => {
    redirectOnNotAuth();
  }
  , [location.pathname])

  const handleLogOut = useCallback(() => {
      logOut();
      redirectOnNotAuth();
  });

  const handleLogIn = useCallback(() => {
    setAuth(isAuthenticated());
    navigate('');
  });

  const handleRedirect = useCallback((to) => {
    navigate(to);
  });

  return(
      
    <div className="App">
        {
          auth?
          <>
            <Navbar bg="primary" variant="dark" expand="lg">
              <Container>
                <Navbar.Brand as={NavLink} to="/">Wordle Tourney</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                  <Nav className="me-auto">
                    <Nav.Link as={NavLink} to="/helper">Helper</Nav.Link>
                    <Nav.Link as={NavLink} to="/dictionary">Dictionary</Nav.Link>
                    <NavDropdown title="Tournaments" id="basic-nav-dropdown">
                      <NavDropdown.Item  as={NavLink} to="/tournaments">My Tournaments</NavDropdown.Item>
                      <NavDropdown.Item  as={NavLink} to="/public-tournaments">Public Tournaments</NavDropdown.Item>
                      <NavDropdown.Divider></NavDropdown.Divider>
                      <NavDropdown.Item as={NavLink} to="/result">Today's Result</NavDropdown.Item>
                    </NavDropdown>                      
                  </Nav>
                  <Nav>
                    <Nav.Link rel="noopener noreferrer"  href='https://www.nytimes.com/games/wordle/index.html'  target="_bank">Play in English</Nav.Link>
                    <Nav.Link rel="noopener noreferrer"  href="https://wordle.danielfrg.com/" target="_blank">Juega en Español</Nav.Link>
                    <Nav.Link onClick={handleLogOut}>Log Out</Nav.Link>
                  </Nav>
                </Navbar.Collapse>
              </Container>
            </Navbar>
          </>
          : 
          <>
          </>
        }        
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='/log-in' element={<LogIn isLoged={handleLogIn}/>} />
          <Route path='/dictionary' element={<Dictionary />} />
          <Route path='/helper' element={<Helper/>} />
          <Route path='/result' element={<Results/>}/>
          <Route path='/public-tournaments' element={<PublicTournaments/>}/>
          <Route path='/tournaments' element={<MyTournaments/>}/>
          <Route path='/tournament/:action/:id' element={<Tournament redirectFromRoot={handleRedirect}/>}/>
          <Route path='/tournament/:action' element={<Tournament redirectFromRoot={handleRedirect}/>}/>
          <Route path='/positions/:id' element={<Positions />}/>
          <Route path='/error' element={<ErrorPage />}/>
          <Route path='*' element={<ErrorPage />}/>
        </Routes> 
    </div>
  );      
}

export default App;
