Metrics Sample
==============

This project contains a simple Servlet application that has been customised to emit metrics from the [WebSphere Liberty](https://developer.ibm.com/wasdev/websphere-liberty/) server and the app using the [Dropwizard Metrics API](http://www.dropwizard.io/). The sample also demonstrates (using Docker containers) how to monitor your Liberty metrics using [collectd](https://collectd.org/), [Graphite](https://graphiteapp.org/), and [Grafana](https://grafana.com/).

The instructions below are minimal so that you can just get the sample running. For more detail and for instructions on how to add metrics to a servlet application, check out [this article on WASdev.net](https://developer.ibm.com/wasdev/docs/collect-metrics-from-liberty-apps-and-display-in-grafana/).

## Running with Maven

To run the entire demonstration:

1. Make sure you have installed Java 8, Docker, and Maven.
2. Clone this repository to your local harddrive:
    ```bash
    git clone https://github.com/WASdev/sample.metrics.git
    ```
3. Change to the `sample.metrics` directory:
    ```bash
    cd sample.metrics
    ```
4. Build the sample:
    ```bash
    mvn clean install
    ```
5. Build the Liberty Docker image:
    ```bash
    docker build -t example:liberty .
    ```
6. Run the Liberty Docker image:
    ```bash
    docker run --name liberty -d -p 9080:9080 -p 9443:9443 example:liberty
    ```
7. Check that the application is running in a browser at http://localhost:9080/MetricsExample
8. Change to the `collectd` directory:
    ```bash
    cd collectd
    ```
9. Change the permissions on the bash script in the directory then run the script:
    ```bash
    chmod 755 collectd_graphite_setup.sh
    ./collectd_graphite_setup.sh
    ```
    (This copies some files collectd needs from the running Liberty container, builds a collectd Docker image, runs a Graphite Docker container from DockerHub, and runs the collectd Docker container.
10. Check that the Graphite application is running and displaying metrics in a browser at http://localhost:80
11. If you want to display the metrics in Grafana:
    ```bash
    docker run -i --name grafana -d -p 3000:3000 --link graphite grafana/grafana
    ```
12. Open Grafana in a browser: http://localhost:3000
13. Add the Graphite server as a data source in Grafana and then import the provided dashboard from `sample.metrics/grafana/MetricsSampleDashboard.json`.


# Notice

© Copyright IBM Corporation 2017.

# License

```text
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
````

[Liberty Maven Plug-in]: https://github.com/WASdev/ci.maven

