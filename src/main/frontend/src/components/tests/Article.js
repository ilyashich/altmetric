import React, {useEffect, useState} from 'react'
import axios from 'axios';
import {Container, Col, Form, Button, Row} from 'react-bootstrap';
import { Link, useHistory } from "react-router-dom";
import "./Article.css";

const ARTICLES_GET_ALL_URL = '/metrics/api/articles';
const ARTICLES_ADD_URL = '/metrics/api/article/add';

export default function Article(){
    const [doiGet, setDoiGet] = useState('');
    const [doiAdd, setDoiAdd] = useState('');
    const [titleAdd, setTitleAdd] = useState('');
    const [authorAdd, setAuthorAdd] = useState('');
    const [doiCompare, setDoiCompare] = useState('');
    const [titleCompare, setTitleCompare] = useState('');
    const [allArticles, setAllArticles] = useState([]);
    const [reload, setReload] = useState(false);

    const history = useHistory();

    useEffect(() => {
        const loadArticles = async () => {
            const response = await axios.get(ARTICLES_GET_ALL_URL);
            setAllArticles(response.data);
        }
        loadArticles();
    }, [reload]);

    const addItemByDoi = () => {
        console.log('You want to add article: ' + doiAdd);
        axios.get(ARTICLES_ADD_URL + "/bydoi?doi=" + doiAdd)
            .then((response) => {
                console.log(response);
                alert('Article successfully added');
                setReload(!reload);
            })
            .catch(error => console.error(error))
    }

    const addItemByTitle = () => {
        console.log('You want to add article: ' + titleAdd + ", author: " + authorAdd);
        axios.get(ARTICLES_ADD_URL + "/bytitle?title=" + titleAdd + "&author=" + authorAdd)
            .then((response) => {
                console.log(response);
                alert('Article successfully added');
                setReload(!reload);
            })
            .catch(error => console.error(error))
    }

    const handleCompareSubmit = (e) => {
        history.push("/metrics/comparemetrics?doi=" + doiCompare + "&title=" + titleCompare);
    }

    const handleGetSubmit = (e) => {
        history.push("/metrics/comparewidgets?doi=" + doiGet);
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

    const handleDoiCompareChange = (e) => {
        setDoiCompare(e.target.value);
        e.preventDefault();
    }

    const handleTitleCompareChange = (e) => {
        setTitleCompare(e.target.value);
        e.preventDefault();
    }

    const renderTable = () => {
        return(
            <table className='table table-striped' aria-labelledby="tabelLabel">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Article Title</th>
                    <th>Added by</th>
                    <th>DOI</th>
                    <th>Link</th>
                </tr>
                </thead>
                <tbody>
                {allArticles.map((article, i) =>
                    getArticleLink(article, i)
                )}
                </tbody>
            </table>

        )
    }

    const getArticleLink = (article, i) => {
        if(article.doi !== null && article.title === null){
            return(
                <tr key={i}>
                    <td>{i+1}</td>
                    <td>
                        {
                            article.mendeley.title === null ?
                            article.crossref.title
                            : article.mendeley.title
                        }
                    </td>
                    <td>doi</td>
                    <td>{article.doi}</td>
                    <td><Link to={"/metrics/details/?doi=" + article.doi}>Open</Link></td>
                </tr>
            );
        }
        if(article.doi === null && article.title !== null){
            return(
                <tr key={i}>
                    <td>{i+1}</td>
                    <td>{article.title}</td>
                    <td>title</td>
                    <td> </td>
                    <td>
                        <Link to={"/metrics/details/?title=" + article.title}>Open</Link>
                    </td>
                </tr>
            );
        }
        return null;
    }

    return (
        <Container>
            <Row>
                <Col>
                    <Row>
                        <Form>
                            <Form.Label>Compare widgets</Form.Label>
                            <Form.Control type="text" placeholder="DOI" value={doiGet} onChange={handleDoiGetChange}/>
                            <Button variant="primary" onClick={handleGetSubmit}>
                                Submit
                            </Button>
                        </Form>
                    </Row>
                    <Row style={{ marginTop: '10px'}}>
                        <Form>
                            <Form.Label>Compare metrics collected by DOI and title</Form.Label>
                            <Form.Group>
                                <Form.Control type="text" placeholder="DOI" value={doiCompare} onChange={handleDoiCompareChange}/>
                            </Form.Group>
                            <Form.Group>
                                <Form.Control type="text" placeholder="Title" value={titleCompare} onChange={handleTitleCompareChange}/>
                            </Form.Group>
                            <Button variant="primary" onClick={handleCompareSubmit}>
                                Submit
                            </Button>
                        </Form>
                    </Row>
                </Col>
                <Col>
                        <Form onSubmit={handleAddByDoiSubmit}>
                            <Form.Label>Add article by doi</Form.Label>
                            <Form.Control type="text" placeholder="DOI" value={doiAdd} onChange={handleDoiAddChange}/>
                            <Button variant="primary" type="submit">
                                Add atricle
                            </Button>
                        </Form>
                </Col>
                <Col>
                    <Form onSubmit={handleAddByTitleSubmit}>
                        <Form.Group>
                            <Form.Label>Enter title</Form.Label>
                            <Form.Control as="textarea" rows={2} type="text" placeholder="Title" value={titleAdd} onChange={handleTitleAddChange}/>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Enter author surname</Form.Label>
                            <Form.Control type="text" placeholder="Author" value={authorAdd} onChange={handleAuthorAddChange}/>
                        </Form.Group>
                        <Button variant="primary" type="submit">
                            Add article
                        </Button>
                    </Form>
                </Col>
            </Row>
            <Row>
                <Col>
                    <h5>
                        <strong>All articles</strong>
                    </h5>
                    {renderTable()}
                </Col>
            </Row>
        </Container>
    );
}