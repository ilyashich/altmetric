import {Col, Container, Row} from "react-bootstrap";
import React from "react";


export default function CompareWidgets(){
    const doi = localStorage.getItem("doi");
    return(
        <Container>
        <Row>
            <h5>
                Metrics Comparison
            </h5>
            <Col>
                <div className="altmetrics-widget" data-doi={doi}> </div>
            </Col>
            <Col>
                <h5>
                    PlumX
                </h5>
                <a href={"https://plu.mx/plum/a/?doi=" + doi} className="plumx-summary"> </a>
            </Col>
            <Col>
                <h5>
                    Altmetric
                </h5>
                <div data-badge-details="right" data-badge-type="medium-donut" data-doi={doi}
                     data-hide-no-mentions="true" className="altmetric-embed"> </div>
            </Col>
        </Row>
        </Container>
    );
}