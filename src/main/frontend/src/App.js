import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css'
import {BrowserRouter, Route, Switch} from "react-router-dom";
import ArticleComponent from "./components/articlemainpage/ArticleComponent";
import {Container, Nav, Navbar} from "react-bootstrap";
import Tests from "./components/tests/Tests";

function App() {

    return (
        <div className="App">
            <BrowserRouter>
                <Navbar className="app-navbar" bg="light" expand="lg">
                    <Container fluid>
                        <Navbar.Brand className="app-navbar-brand">
                            Altmertic System
                        </Navbar.Brand>
                        <Navbar.Collapse id="responsive-navbar-nav">
                            <Nav className="me-auto">
                                <Nav.Link href="/metrics/tests">Tests</Nav.Link>
                            </Nav>
                        </Navbar.Collapse>
                    </Container>
                </Navbar>
                <Switch>
                    <Route path={"/metrics/details/"}>
                        <ArticleComponent />
                    </Route>
                    <Route path="/metrics/tests">
                        <Tests />
                    </Route>
                </Switch>
            </BrowserRouter>
        </div>
    );
}

export default App;
