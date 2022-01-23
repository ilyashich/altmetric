import {Route, Switch, useRouteMatch} from "react-router-dom";
import Article from "./Article";
import CompareWidgets from "./CompareWidgets";
import CompareMetrics from "./CompareMetrics";


export default function Tests(){
    let { path, url } = useRouteMatch();

    return(
        <Switch>
            <Route exact path={path}>
                <Article />
            </Route>
            <Route path={`${url}/comparewidgets`}>
                <CompareWidgets />
            </Route>
            <Route path={`${url}/comparemetrics`}>
                <CompareMetrics />
            </Route>
        </Switch>
    );
}