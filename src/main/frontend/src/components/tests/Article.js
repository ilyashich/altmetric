import React, {useEffect, useState} from 'react'
import axios from 'axios';
import {Container, Col, Form, Button, Row} from 'react-bootstrap';
import { useHistory } from "react-router-dom";
import "./Article.css";
import ArticlesTable from "./ArticlesTable";

const ARTICLES_GET_ALL_URL = '/metrics/api/articles';
const ARTICLES_ADD_URL = '/metrics/api/article/add';

export default function Article(){
    const [doiGet, setDoiGet] = useState('');
    const [doiAdd, setDoiAdd] = useState('');
    const [titleAdd, setTitleAdd] = useState('');
    const [authorAdd, setAuthorAdd] = useState('');
    const [doiCompare, setDoiCompare] = useState('');
    const [titleCompare, setTitleCompare] = useState('');
    const [authorCompare, setAuthorCompare] = useState('');
    const [allArticles, setAllArticles] = useState([]);
    const [loaded, setLoaded] = useState(false);
    const [reload, setReload] = useState(false);

    const history = useHistory();

    useEffect(() => {
        const loadArticles = async () => {
            const response = await axios.get(ARTICLES_GET_ALL_URL);
            setAllArticles(response.data);
            setLoaded(true);
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
        history.replace("/metrics/tests/comparemetrics?doi=" + doiCompare +
            "&title=" + titleCompare +
            "&author=" + authorCompare);
    }

    const handleGetSubmit = (e) => {
        history.replace("/metrics/tests/comparewidgets?doi=" + doiGet);
    }

    const handleAddByDoiSubmit = (e) => {
        addItemByDoi();
        e.preventDefault();
    }

    const handleAddByTitleSubmit = (e) => {
        addItemByTitle();
        e.preventDefault();
    }

    const onInputChange = (e, setState) => {
        setState(e.target.value);
        e.preventDefault();
    }


    return (
        <Container>
            <Row>
                <Col>
                    <Row>
                        <Form>
                            <Form.Label>Compare widgets</Form.Label>
                            <Form.Control
                                type="text"
                                placeholder="DOI"
                                value={doiGet}
                                onChange={event => onInputChange(event, setDoiGet)}
                            />
                            <Button variant="primary" onClick={handleGetSubmit}>
                                Submit
                            </Button>
                        </Form>
                    </Row>
                    <Row style={{ marginTop: '10px'}}>
                        <Form>
                            <Form.Label>Compare metrics collected by DOI and title</Form.Label>
                            <Form.Group>
                                <Form.Control
                                    type="text"
                                    placeholder="DOI"
                                    value={doiCompare}
                                    onChange={event => onInputChange(event, setDoiCompare)}
                                />
                            </Form.Group>
                            <Form.Group>
                                <Form.Control
                                    type="text"
                                    placeholder="Title"
                                    value={titleCompare}
                                    onChange={event => onInputChange(event, setTitleCompare)}
                                />
                            </Form.Group>
                            <Form.Group>
                                <Form.Control
                                    type="text"
                                    placeholder="Author"
                                    value={authorCompare}
                                    onChange={event => onInputChange(event, setAuthorCompare)}
                                />
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
                            <Form.Control
                                type="text"
                                placeholder="DOI"
                                value={doiAdd}
                                onChange={event => onInputChange(event, setDoiAdd)}
                            />
                            <Button variant="primary" type="submit">
                                Add atricle
                            </Button>
                        </Form>
                </Col>
                <Col>
                    <Form onSubmit={handleAddByTitleSubmit}>
                        <Form.Group>
                            <Form.Label>Enter title</Form.Label>
                            <Form.Control
                                as="textarea"
                                rows={2}
                                type="text"
                                placeholder="Title"
                                value={titleAdd}
                                onChange={event => onInputChange(event, setTitleAdd)}
                            />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Enter author surname</Form.Label>
                            <Form.Control
                                type="text"
                                placeholder="Author"
                                value={authorAdd}
                                onChange={event => onInputChange(event, setAuthorAdd)}
                            />
                        </Form.Group>
                        <Button variant="primary" type="submit">
                            Add article
                        </Button>
                    </Form>
                </Col>
            </Row>
            <Row>
                {loaded && <ArticlesTable articles={allArticles} />}
            </Row>
        </Container>
    );
}