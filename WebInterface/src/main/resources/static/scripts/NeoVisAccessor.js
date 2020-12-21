function draw(company_name){
var config ={
            container_id: "viz",
            server_url: "bolt://localhost:7687",
            server_user: "java_application",
            server_password: "12345",
            labels: {
                "tweet": {
                    tweet: "tweet"
                },
                "company":{
                    username: "username"
                }
            },
            relationships: {
                "TWEETED":{
                    caption: false

                }
            },
            initial_cypher: "MATCH p = (c:company{username:'"+company_name+"'})-[:TWEETED]->(t:tweet) RETURN p limit 30"
        }

        var viz = new NeoVis.default(config);
        viz.render();
}

/*


*/