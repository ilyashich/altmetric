import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css'
import {BrowserRouter, Route, Link, Switch} from "react-router-dom";
import ArticleComponent from "./components/articlemainpage/ArticleComponent";
import Article from "./components/tests/Article";
import CompareWidgets from "./components/tests/CompareWidgets";
import {Container, Navbar} from "react-bootstrap";
import CompareMetrics from "./components/tests/CompareMetrics";

function App() {

    return (
        <div className="App">
            <BrowserRouter>
                <Navbar className="app-navbar" bg="light" expand="lg">
                    <Container fluid>
                        <Navbar.Brand className="app-navbar-brand">
                            Altmertic System
                        </Navbar.Brand>
                    </Container>
                </Navbar>
                <Switch>
                    <Route path={"/metrics/details/"}>
                        <ArticleComponent />
                    </Route>
                    <Route path="/metrics/tests">
                        <Article />
                    </Route>
                    <Route path="/metrics/comparewidgets">
                        <CompareWidgets />
                    </Route>
                    <Route path="/metrics/comparemetrics">
                        <CompareMetrics />
                    </Route>
                    <Route path="/metrics">
                        <Link to="/metrics/tests">Tests</Link>
                    </Route>
                </Switch>
            </BrowserRouter>
        </div>
    );
}

export default App;
