import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css'
import {BrowserRouter, Route, Link, Switch} from "react-router-dom";
import axios from "axios";
import ArticleComponent from "./components/ArticleComponent";
import {useEffect, useState} from "react";
import {Row} from "react-bootstrap";
import Article from "./components/Article";
import ArticleWidget from "./components/ArticleWidget";

const ARTICLES_URL = 'http://localhost:8080/api/articles';

function App() {

    const [allArticles, setAllArticles] = useState([]);
    useEffect(() => {
        axios.get(ARTICLES_URL).then((response) => setAllArticles(response.data));
    }, []);

    return (
        <div className="App">
            <BrowserRouter>
                <Switch>
                    <Route path={"/details/"}>
                        <ArticleComponent />
                    </Route>
                    <Route path="/add">
                        <Article />
                    </Route>
                    <Route path="/widget">
                        <ArticleWidget doi="10.1038/s41591-020-0820-9" />
                    </Route>
                    <Route path="/">
                        {allArticles.map((article, i) =>
                            <Row key={i}>
                                <div>DOI: {article.doi}</div>
                                <div>Title: {article.mendeley.title}</div>
                                <Link to={"/details/?doi=" + article.doi}>Link</Link>

                            </Row>
                        )}
                        <Link to="/add">Add article</Link>
                        <Row>
                            <Link to="/widget">Widget</Link>
                        </Row>
                    </Route>
                </Switch>
            </BrowserRouter>
        </div>
    );
}

export default App;
