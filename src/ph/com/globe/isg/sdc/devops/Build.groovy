package ph.com.globe.isg.sdc.devops

/**
 * @author butch* Created on 14/06/2020.
 */
class Build implements Serializable {
    private final def script

    Build(def script) {
        this.script = script
    }

    void setBuildDescription(Map args) {
        script.currentBuild.displayName = args.title
        script.currentBuild.description = args.description
    }
}
