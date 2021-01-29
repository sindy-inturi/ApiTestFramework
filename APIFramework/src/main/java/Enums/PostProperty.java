package Enums;

public enum PostProperty {
	USERID{
	    @Override
	    public String toString() {
	      return "userId";
	    }
	  },
	ID{
		@Override
	    public String toString() {
	      return "id";
	    }
	  },
	TITLE{
		@Override
	    public String toString() {
	      return "title";
	    }
	  },
	BODY{
		@Override
	    public String toString() {
	      return "body";
	    }
	  }

}
