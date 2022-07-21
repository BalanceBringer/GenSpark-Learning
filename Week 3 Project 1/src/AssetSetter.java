public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

    }
    public void setNPC() {
        gp.ent[0] = new NPCOldMan(gp);
        gp.ent[0].worldX = gp.tileSize * 21;
        gp.ent[0].worldY = gp.tileSize * 21;
    }
    public void setMob() {
        gp.mob[0] = new MobGoblin(gp);
        gp.mob[0].worldX = gp.tileSize * 23;
        gp.mob[0].worldY = gp.tileSize * 36;

        gp.mob[1] = new MobGoblin(gp);
        gp.mob[1].worldX = gp.tileSize * 23;
        gp.mob[1].worldY = gp.tileSize * 37;
    }

}
