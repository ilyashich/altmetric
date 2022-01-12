import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css'
import {BrowserRouter, Route, Link, Switch} from "react-router-dom";
import ArticleComponent from "./components/articlemainpage/ArticleComponent";
import Article from "./components/Article";

function App() {

    return (
        <div className="App">
            <BrowserRouter>
                <Switch>
                    <Route path={"/metrics/details/"}>
                        <ArticleComponent />
                    </Route>
                    <Route path="/metrics/add">
                        <Article />
                    </Route>
                    <Route path="/metrics">
                        <Link to="/metrics/add">Add article</Link>
                    </Route>
                </Switch>
            </BrowserRouter>
        </div>
    );
}

export default App;
