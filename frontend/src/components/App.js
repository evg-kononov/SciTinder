import React from "react";
import {Router, Route} from "react-router-dom";

import Header from "./Header";
import MainPage from "./MainPage";
import AboutUs from "./AboutUs";
import SciFinder from "./sci_finder/SciFinder";
import history from "../history";
import SearchByPrompt from "./sci_finder/searchForms/SearchByPrompt";

const App = () => {
    return (
        <div className="ui container">
            <Router history={history}>
                <div>
                    <Header/>
                    <Route path="/" exact component={MainPage}/>
                    <Route path="/about-us" exact component={AboutUs}/>
                    <Route path="/sci-finder" exact component={SciFinder}/>
                    <Route path="/prompt" exact component={SearchByPrompt}/>
                </div>
            </Router>
        </div>
    );
};

export default App;