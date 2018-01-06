package yw29_zl58.main.model.data;

import common.*;
import common.datatype.chatroom.*;

/**
 * the data type of telling others there's an error when executing the message
 * @author wangye
 *
 */
public class ExceptionType implements ICRExceptionStatusType {
	

		private static final long serialVersionUID = -45788488065782391L;

		private DataPacketCR<?> packet;
		
		private String info;
		
		/**
		 * constructor of the data
		 * @param packet the data package of the error
		 * @param info the information about the message
		 */
		public ExceptionType(DataPacketCR<?> packet, String info) {
			this.packet = packet;
			this.info = info;
		}
		
		@Override
		public DataPacketCR<?> getOriginalData() {
			return packet;
		}

		@Override
		public String getFailureInfo() {
			return info;
		}

	}
