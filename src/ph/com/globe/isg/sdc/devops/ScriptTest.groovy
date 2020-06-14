package ph.com.globe.isg.sdc.devops

/**
 * @author butch* Created on 14/06/2020.
 */
class ScriptTest implements Serializable {

    private final def script

    ScriptTest(script) {
        this.script = script
    }

    def stuff(script) {
        script.node {
            script.echo "running in ${script.env.JENKINS_URL}"
        }
    }
}
