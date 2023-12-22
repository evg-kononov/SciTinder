import React from "react";
import Checkbox from "./Checkbox";

class Filters extends React.Component {
    render() {
        return (
            <div>
                <Checkbox label="Exclude Coauthors" onClick={this.props.onCheckboxClick}/>
            </div>
        );
    }
}

export default Filters;