import {Col, Container, Row} from "react-bootstrap";
import React, {useEffect} from "react";
import {useLocation} from "react-router-dom";


export default function CompareWidgets( ){
    const { search } = useLocation();
    const params = new URLSearchParams(search);
    const doi = params.get("doi");

    useEffect(() => {
        const plumxScript = document.getElementById("plumx");

        if (!plumxScript) {
            const script = document.createElement('script');
            script.src = "//cdn.plu.mx/widget-summary.js";
            script.id = "plumx";
            document.body.appendChild(script);
        }

        const altmetricScript = document.getElementById("altmetric");

        if (!altmetricScript) {
            const script = document.createElement('script');
            script.src = "https://d1bxh8uas1mnw7.cloudfront.net/assets/embed.js";
            script.id = "altmetric";
            document.body.appendChild(script);
        }

        const customScript = document.getElementById("custom-script");

        if (!customScript) {
            const script = document.createElement('script');
            script.src = "/metrics/widget/index.js";
            script.id = "custom-script";
            document.body.appendChild(script);
        }

        return () => {
            if (plumxScript) {
                plumxScript.remove();
            }
            if (altmetricScript) {
                altmetricScript.remove();
            }
            if (customScript) {
                customScript.remove();
            }
        };
    }, [doi]);

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