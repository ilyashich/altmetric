import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css'
import {BrowserRouter, Route, Link, Switch} from "react-router-dom";
import axios from "axios";
import ArticleComponent from "./ArticleComponent";
import {useEffect, useState} from "react";
import {Row} from "react-bootstrap";

const ARTICLES_URL = 'http://localhost:8080/articles';

function App() {

    const [allArticles, setAllArticles] = useState([]);
    useEffect(() => {
        axios.get(ARTICLES_URL).then((response) => setAllArticles(response.data));
    }, [])

    return (
        <div className="App">
            <BrowserRouter>
                <Switch>
                    {allArticles.map(article =>
                    <Route key={article.doi} path={"/details/" + article.doi}>
                        <ArticleComponent article={article} />
                    </Route>
                    )}
                    <Route path="/">
                        {allArticles.map(article =>
                            <Row key={article.doi}>
                                <div>DOI: {article.doi}</div>
                                <div>Title: {article.mendeley.title}</div>
                                <Link to={"/details/" + article.doi}>Link</Link>
                            </Row>
                        )}
                    </Route>
                </Switch>
            </BrowserRouter>
        </div>
    );
}

export default App;
