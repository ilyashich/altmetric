import React, {useEffect, useState} from 'react'
import axios from 'axios';
import {Row, Col, Form} from 'react-bootstrap';
import CompareWidgets from "./CompareWidgets";
import {Link} from "react-router-dom";

const ARTICLES_GET_ALL_URL = '/metrics/api/articles';
const ARTICLES_ADD_URL = '/metrics/api/article/add';

export default function Article(){
    const [doiGet, setDoiGet] = useState('');
    const [doiAdd, setDoiAdd] = useState('');
    const [titleAdd, setTitleAdd] = useState('');
    const [authorAdd, setAuthorAdd] = useState('');
    const [allArticles, setAllArticles] = useState([]);

    useEffect(() => {
        const loadArticles = async () => {
            const response = await axios.get(ARTICLES_GET_ALL_URL);
            setAllArticles(response.data);
        }
        loadArticles();
    }, [])

    const addItemByDoi = () => {
        console.log('You want to add article: ' + doiAdd);
        axios.get(ARTICLES_ADD_URL + "/bydoi?doi=" + doiAdd)
            .then((response) => {
                console.log(response)
                alert('Article successfully added')
            })
            .catch(error => console.error(error))
    }

    const addItemByTitle = () => {
        console.log('You want to add article: ' + titleAdd + ", author: " + authorAdd);
        axios.get(ARTICLES_ADD_URL + "/bytitle?title=" + titleAdd + "&author=" + authorAdd)
            .then((response) => {
                console.log(response)
                alert('Article successfully added')
            })
            .catch(error => console.error(error))
    }

    const handleGetSubmit = (e) => {
        localStorage.setItem("doi", doiGet);
        window.location.reload();
    }

    const handleAddByDoiSubmit = (e) => {
        addItemByDoi();
        e.preventDefault();
    }

    const handleAddByTitleSubmit = (e) => {
        addItemByTitle();
        e.preventDefault();
    }

    const handleDoiGetChange = (e) => {
        setDoiGet(e.target.value);
        e.preventDefault();
    }

    const handleDoiAddChange = (e) => {
        setDoiAdd(e.target.value);
        e.preventDefault();
    }

    const handleTitleAddChange = (e) => {
        setTitleAdd(e.target.value);
        e.preventDefault();
    }

    const handleAuthorAddChange = (e) => {
        setAuthorAdd(e.target.value);
        e.preventDefault();
    }

    const getArticleLink = (article) => {
        if(article.doi !== null && article.title === null){
            return <Link to={"/metrics/details/?doi=" + article.doi}>{article.doi}</Link>;
        }
        if(article.doi === null && article.title !== null){
            return <Link to={"/metrics/details/?title=" + article.title}>{article.title}</Link>;
        }
        return null;
    }

    return (
        <Row>
            <Col>
                <Form onSubmit={handleGetSubmit}>
                    <label>Get article widget</label>
                    <br/>
                    <input type="text" value={doiGet} onChange={handleDoiGetChange}/>
                    <br/>
                    <input type="submit" value="Confirm"/>
                </Form>
                <CompareWidgets />
            </Col>
            <Col sm={2}>
                <Row>
                    <Form onSubmit={handleAddByDoiSubmit}>
                        <label>Add article by doi</label>
                        <br/>
                        <input type="text" placeholder="DOI" value={doiAdd} onChange={handleDoiAddChange}/>
                        <br/>
                        <input type="submit" value="Confirm"/>
                    </Form>
                </Row>
                <Row>
                    <Form onSubmit={handleAddByTitleSubmit}>
                        <label>Add article by title and author</label>
                        <br/>
                        <input type="text" placeholder="Title" value={titleAdd} onChange={handleTitleAddChange}/>
                        <br/>
                        <input type="text" placeholder="Author" value={authorAdd} onChange={handleAuthorAddChange}/>
                        <br/>
                        <input type="submit" value="Confirm"/>
                    </Form>
                </Row>
            </Col>
            <Col sm={3}>
                <h5>
                    <strong>All articles</strong>
                </h5>
                {allArticles.map((article, i) =>
                    <div key={i}>
                        {getArticleLink(article)}
                    </div>
                )}
            </Col>
        </Row>
    );
}