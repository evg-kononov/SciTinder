import React from "react";
import {Link} from "react-router-dom";

const MainPage = () => {
    return (
        <div>
            <h1>Hello, this is first version of SciTinder Web App!</h1>
            <Link to="/sci-finder" className="ui primary button">
                Find the closest scientists
            </Link>

            <Link to="/prompt" className="ui olive button">
                Find the closest scientists by prompt
            </Link>
        </div>
    );
};

export default MainPage;