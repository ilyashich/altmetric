import React, {useState} from 'react'
import axios from 'axios';
import {Row, Col, Form, Container} from 'react-bootstrap';
import ArticleWidget from "./ArticleWidget";

export default function Article(){
    const ARTICLES_URL = '/metrics/api/articles';
    const [doiGet, setDoiGet] = useState('');
    const [doiAdd, setDoiAdd] = useState('');
    const [showWidget, setShowWidget] = useState(false);

    function addItem(){
        console.log('You want to add article: ' + doiAdd);
        axios.post(ARTICLES_URL, doiAdd, { headers: { 'Content-Type' : 'text/plain'} })
            .then((response) => {
                console.log(response)
                alert('Article successfully added')
            })
            .catch(error => console.error(error))
    }

    function handleGetSubmit(e) {
        setShowWidget(true);
        e.preventDefault();
    }

    function handleAddSubmit(e) {
        addItem();
        e.preventDefault();
    }

    function handleDoiGetChange(e) {
        setDoiGet(e.target.value);
        e.preventDefault();
    }
    function handleDoiAddChange(e) {
        setDoiAdd(e.target.value);
        e.preventDefault();
    }

    return (
        <Container>
            <Row>
                <Col>
                    <Form onSubmit={handleGetSubmit}>
                        <label>Get article widget</label>
                        <br/>
                        <input type="text" value={doiGet} onChange={handleDoiGetChange}/>
                        <br/>
                        <input type="submit" value="Confirm"/>
                    </Form>
                    {showWidget &&
                        <ArticleWidget doi={doiGet} />
                    }
                </Col>
                <Col>
                    <Form onSubmit={handleAddSubmit}>
                        <label>Add article</label>
                        <br/>
                        <input type="text" value={doiAdd} onChange={handleDoiAddChange}/>
                        <br/>
                        <input type="submit" value="Confirm"/>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
}