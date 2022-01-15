import React, {useEffect, useState} from 'react'
import axios from 'axios';
import {Row, Col, Form} from 'react-bootstrap';
import CompareWidgets from "./CompareWidgets";

export default function Article(){
    const ARTICLES_URL = 'http://localhost:8080/api/articles';
    const [doiGet, setDoiGet] = useState('');
    const [doiAdd, setDoiAdd] = useState('');
    const [allDois, setAllDois] = useState([]);

    useEffect(() => {
        const loadArticles = async () => {
            const response = await axios.get(ARTICLES_URL);
            setAllDois(response.data);
        }
        loadArticles();
    }, [])

    const addItem = () => {
        console.log('You want to add article: ' + doiAdd);
        axios.post(ARTICLES_URL, doiAdd, { headers: { 'Content-Type' : 'text/plain'} })
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

    const handleAddSubmit = (e) => {
        addItem();
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
                <Form onSubmit={handleAddSubmit}>
                    <label>Add article</label>
                    <br/>
                    <input type="text" value={doiAdd} onChange={handleDoiAddChange}/>
                    <br/>
                    <input type="submit" value="Confirm"/>
                </Form>
            </Col>
            <Col sm={3}>
                <h5>
                    <strong>All articles</strong>
                </h5>
                {allDois.map((article, i) =>
                    <div key={i}>
                        {article.doi}
                    </div>
                )}
            </Col>
        </Row>
    );
}