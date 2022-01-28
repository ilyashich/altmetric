import {useLocation} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";
import {Container} from "react-bootstrap";
import AuthorArticlesTable from "./AuthorArticlesTable";

const ARTICLE_URL = "/metrics/api/articles/author";

export default function AuthorPage() {

    const [articles, setArticles] = useState([]);
    const [loaded, setLoaded] = useState(false);
    const { search } = useLocation();
    const params = new URLSearchParams(search);
    const name = params.get("authorName");
    const surname = params.get("authorSurname");

    useEffect(() => {
        const loadArticles = async () => {
            const response = await  axios.get(ARTICLE_URL + "?authorName=" + name + "&authorSurname=" + surname);
            setArticles(response.data);
            setLoaded(true);
        }

        loadArticles();
    }, [name, surname]);

    return(
        <Container>
            <h1>
                {name + " " + surname}
            </h1>
            {loaded && <AuthorArticlesTable articles={articles} />}
        </Container>
    );
}